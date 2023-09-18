package com.pandasdroid.rvexample;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pandasdroid.rvexample.databinding.View2Binding;
import java.util.ArrayList;

public class AdapterSubTitles extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SubTitlesModel> list = new ArrayList<>();

    public AdapterSubTitles(ArrayList<SubTitlesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderSubTitles(View2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderSubTitles h = (ViewHolderSubTitles) holder;
        SubTitlesModel item = list.get(position);
        h.binding.tvTitle.setText(item.title);
        final LinearLayout[] llHorizontal = {createNewHorizontalLayout(h.itemView.getContext())};
        h.binding.llVertical.addView(llHorizontal[0]);
        h.binding.llVertical.post(new Runnable() {
            @Override
            public void run() {
                int screenWidth = h.binding.llVertical.getWidth() - 20;
                int currentLineWidth = 0;
                for (String text : item.listTitles) {
                    TextView textView = createTextView(holder.itemView.getContext(), text);

                    // Measure the text view to get its width
                    textView.measure(0, 0);
                    int textViewWidth = textView.getMeasuredWidth();


                    // Check if adding the current text view exceeds the screen width
                    if (currentLineWidth + textViewWidth > screenWidth) {
                        // Create a new LinearLayout and add the text view to it
                        llHorizontal[0] = createNewHorizontalLayout(h.itemView.getContext());
                        h.binding.llVertical.addView(llHorizontal[0]);
                        currentLineWidth = 0; // Reset the current line width
                    }

                    // Add the text view to the current horizontal layout
                    llHorizontal[0].addView(textView);
                    currentLineWidth += textViewWidth;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private TextView createTextView(Context context, String text) {
        TextView textView = new TextView(context);
        textView.setMaxLines(1);

        // Create layout params with margins
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set margins (left, top, right, bottom) in pixels
        layoutParams.setMargins(
                (int) context.getResources().getDimension(com.intuit.sdp.R.dimen._3sdp),
                (int) context.getResources().getDimension(com.intuit.sdp.R.dimen._2sdp),
                0,
                0
        );

        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        textView.setPadding(16, 8, 16, 8);

        // Set background drawable
        textView.setBackground(context.getResources().getDrawable(R.drawable.border_unselected));

        return textView;
    }


    private LinearLayout createNewHorizontalLayout(Context context) {
        LinearLayout newLinearLayout = new LinearLayout(context);
        newLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newLinearLayout.setGravity(Gravity.START);
        return newLinearLayout;
    }

    public static class ViewHolderSubTitles extends RecyclerView.ViewHolder {

        View2Binding binding;

        public ViewHolderSubTitles(@NonNull View2Binding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
