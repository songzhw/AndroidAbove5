package ca.six.and7.scoped_directory;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ca.six.and7.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-20.
 */

public class ScopedDirectoryDemo extends AppCompatActivity {
    private StorageManager mStorageManger;
    private List<String> mListItems = new ArrayList<>();
    private DirectoryRecyclerAdapter mDirectoryAdapter;
    private RecyclerView mRecyclerView;

    private static final int DIRECTORY_REQUEST_CODE = 1;
    private static final String[] DIRECTORIES = {
            Environment.DIRECTORY_MOVIES,
            Environment.DIRECTORY_DOCUMENTS,
            Environment.DIRECTORY_DOWNLOADS,
            Environment.DIRECTORY_MUSIC};
    private static final String[] DIRECTORIES_PROJECTS = {
            DocumentsContract.Document.COLUMN_DISPLAY_NAME,
            DocumentsContract.Document.COLUMN_MIME_TYPE,
            DocumentsContract.Document.COLUMN_DOCUMENT_ID,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        mStorageManger = getSystemService(StorageManager.class);
        ListView lvDirectory = (ListView) findViewById(R.id.lv_directory);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_directory);

        List<String> data = new ArrayList<>();
        data.add("Movie");
        data.add("Documents");
        data.add("Downloads");
        data.add("Music");

        lvDirectory.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, data));
        lvDirectory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StorageVolume storageVolume = mStorageManger.getPrimaryStorageVolume();
                Intent storageIntent = storageVolume.createAccessIntent(getDirectoryName(position));
                startActivityForResult(storageIntent, DIRECTORY_REQUEST_CODE);
            }
        });

        mDirectoryAdapter = new DirectoryRecyclerAdapter(this, mListItems);
        mRecyclerView.setAdapter(mDirectoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private String getDirectoryName(int pos) {
        return DIRECTORIES[pos];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == DIRECTORY_REQUEST_CODE) {
            getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updateList(data.getData());
        }
    }

    private void updateList(Uri uri) {
        mListItems.clear();
        ContentResolver contentResolver = getContentResolver();
        Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri));

        try (Cursor childCursor = contentResolver.query(childrenUri, DIRECTORIES_PROJECTS, null, null, null)) {
            while (childCursor != null && childCursor.moveToNext()) {
                    mListItems.add(childCursor.getString(childCursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME)));
            }

            if(mListItems.isEmpty() && !contentResolver.getPersistedUriPermissions().isEmpty()){
                mListItems.add("Nothing in the directory.");
            }

            mDirectoryAdapter.setDataSource(mListItems);
        }
    }

}
