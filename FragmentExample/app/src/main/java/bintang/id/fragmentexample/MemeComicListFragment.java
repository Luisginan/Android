package bintang.id.fragmentexample;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MemeComicListFragment extends Fragment {

    private int[] imageResIds;
    private String[] imageNames;
    private String[] imageDescriptions;
    private String[] imageUrls;


    private OnRageComicSelected listener;

    public MemeComicListFragment() {
    }

    public static MemeComicListFragment newInstance() {
        return new MemeComicListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ShowMainActivityVariable(context);

        ValidateInterface(context);

        final Resources resources = getResources(context);

        FillImageMemeComic(resources);
    }

    private void ValidateInterface(Context context) {
        if (context instanceof OnRageComicSelected) {
            listener = (OnRageComicSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnRageComicSelected.");
        }
    }

    private void ShowMainActivityVariable(Context context) {
        if(context != null){
            MainActivity mainActivity = (MainActivity) getContext();
            Toast.makeText(getContext(), mainActivity.myVariable, Toast.LENGTH_LONG).show();
        }
    }

    private void FillImageMemeComic(Resources resources) {
        final TypedArray typedArray = resources.obtainTypedArray(R.array.images);
        final int imageCount = imageNames.length;
        imageResIds = new int[imageCount];
        for (int i = 0; i < imageCount; i++) {
            imageResIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }

    @NonNull
    private Resources getResources(Context context) {
        final Resources resources = context.getResources();
        imageNames = resources.getStringArray(R.array.names);
        imageDescriptions = resources.getStringArray(R.array.descriptions);
        imageUrls = resources.getStringArray(R.array.urls);
        return resources;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_meme_comic_list, container, false);

        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(new RageComicAdapter(activity));
        return view;
    }

    public interface OnRageComicSelected {
        void onMemeComicSelected(int imageResId, String name, String description, String url);
    }

    class RageComicAdapter extends RecyclerView.Adapter<ViewHolder> {

        private LayoutInflater mLayoutInflater;

        public RageComicAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new ViewHolder(mLayoutInflater
                    .inflate(R.layout.recyler_item_meme_comic, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            final int imageResId = imageResIds[position];
            final String name = imageNames[position];
            final String description = imageDescriptions[position];
            final String url = imageUrls[position];
            viewHolder.setData(imageResId, name);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMemeComicSelected(imageResId, name, description, url);
                }
            });
        }

        @Override
        public int getItemCount() {
            return imageNames.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        private ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.comic_image);
            textView = (TextView) itemView.findViewById(R.id.name);
        }

        private void setData(int imageResId, String name) {
            imageView.setImageResource(imageResId);
            textView.setText(name);
        }
    }
}
