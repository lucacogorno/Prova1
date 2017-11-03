package com.example.lcogorno.prova1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by lcogorno on 03/11/2017.
 */

public class AppToDisableIterator implements Iterator {
    Context context;
    LinkedList<Intent> intents;
    String [] names = new String[]{"com.google.android.youtube", "com.android.galaxy4"};

    public AppToDisableIterator()
    {
        intents = new LinkedList<>();
        for (String s : names)
        {
            Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + s));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intents.add(i);
        }
    }

    @Override
    public boolean hasNext() {
        return intents.isEmpty();
    }

    @Override
    public Intent next() {
        if(hasNext()) {
            return intents.getFirst();
        }
        return null;
    }

    @Override
    public void remove() {
        intents.removeFirst();
    }

    @Override
    public void forEachRemaining(Consumer action) {
    }
}
