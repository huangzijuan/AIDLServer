package com.example.zijuan.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
    private static final String TAG = "AIDLService Server";

    private List<Book> bookList = new ArrayList<>();

    public AIDLService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initData();
    }

    private void initData() {
        bookList.add(new Book("西游记"));
        bookList.add(new Book("红楼梦"));
        bookList.add(new Book("西厢记"));
        bookList.add(new Book("四书五经"));
        bookList.add(new Book("程序设计"));
    }

    private final BookController.Stub stub = new BookController.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBookInOut(Book book) throws RemoteException {
            if (book != null) {
                book.setName("服务器改了新书的名字 InOut");
                bookList.add(book);
            } else {
                Log.e(TAG, "接收到了一个空对象 InOut");
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
