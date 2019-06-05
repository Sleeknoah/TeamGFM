package com.thegloriousfountainministries.exp2.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.FileDownload;
import com.thegloriousfountainministries.exp2.custom.PaidModel;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.data.FavDb;
import com.thegloriousfountainministries.exp2.pages.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 3/31/2017.
 */

  public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.MyViewHolder> {

    private Context mContext;
    private  List<PaidModel> albumList;
    char naira = '\u20A6';
    CartDb helper;
    FavDb helper2;
    PaidModel album;
    RelativeLayout loader;

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title_book, url;
        public Button download;


        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            title_book =(TextView)itemView.findViewById(R.id.titleDown);
            url = itemView.findViewById(R.id.uurl);
            download = itemView.findViewById(R.id.down);
        }
    }



    public DownloadAdapter(Context mContext, List<PaidModel> albumList) {
        this.mContext = mContext;
        this.albumList =albumList;
    }
    public void setBookList(List<PaidModel> movieList) {
        this.albumList = movieList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.download, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        album = albumList.get(position);
        holder.title_book.setText(album.getBook_title());
        holder.url.setText(album.getUrlData());

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getRetrofitImage();
            }
        });

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }




    void getRetrofitImage() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.thegloriousfountainministries.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FileDownload service = retrofit.create(FileDownload.class);

        Call<ResponseBody> call = service.downloadFile();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                try {

                    Log.d("onResponse", "Response came from server");

                           DownloadAsyntask downloadAsyntask = new DownloadAsyntask();
                           downloadAsyntask.execute(response.body());


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    Toast.makeText(mContext, "There is an error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }



    private boolean DownloadImage(ResponseBody body) {

        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                out = new FileOutputStream(mContext.getExternalFilesDir(null) + File.separator + "Android.jpg");
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            }
            catch (IOException e) {
                Log.d("DownloadImage",e.toString());
                return false;
            }
            finally {
                if (in != null) {
//                    try {
//                        in.close();
//                    }catch (IOException e){
//                        Log.d("Close Error", e.getMessage());
//                    }

                }
                if (out != null) {
                    out.close();
                }
            }


            return true;

        } catch (IOException e) {
            Log.d("DownloadImage",e.toString());
            return false;
        }
    }


    class DownloadAsyntask extends AsyncTask<ResponseBody, Void, Void>{


        @Override
        protected Void doInBackground(ResponseBody... responseBodies) {
            saveToDisk(responseBodies[0], "teamgfm" + ".jpg");
            return null;
        }
    }
    private void saveToDisk(ResponseBody body, String filename) {
        try {

            File destinationFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(destinationFile);
                byte data[] = new byte[4096];
                int count;
                int progress = 0;
                long fileSize = body.contentLength();
                Log.d("download sixe", "File Size=" + fileSize);
                while ((count = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, count);
                    progress += count;

                    Log.d("pro", "Progress: " + progress + "/" + fileSize + " >>>> " + (float) progress / fileSize);
                }

                outputStream.flush();

                return;
            } catch (IOException e) {
                e.printStackTrace();

                Log.d("error", "Failed to save the file!");
                return;
            } finally {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("failed", "Failed to save the file!");
            return;
        }
    }

}
