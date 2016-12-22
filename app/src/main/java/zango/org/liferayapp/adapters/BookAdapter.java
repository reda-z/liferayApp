package zango.org.liferayapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zango.org.liferayapp.R;
import zango.org.liferayapp.models.Book;

/**
 * Created by reda on 16/11/16.
 */

public class BookAdapter extends BaseAdapter {

    List<Book> books=new ArrayList<>();
    Context context;


    public BookAdapter(Context context, List<Book> books) {
        this.context=context;
        this.books=books;

    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.item_book, null);
        TextView bookName=(TextView) view.findViewById(R.id.bookName);
        TextView bookAuthor=(TextView) view.findViewById(R.id.bookAuthor);
        TextView bookResume=(TextView) view.findViewById(R.id.bookResume);

        bookName.setText(books.get(i).getNameBook());
        bookAuthor.setText(books.get(i).getAuthorBook());
        bookResume.setText(books.get(i).getResumeBook());

        return view;
    }
}
