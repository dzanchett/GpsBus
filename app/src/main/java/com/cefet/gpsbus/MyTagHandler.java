package com.cefet.gpsbus;

import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Html.TagHandler;
import android.util.Log;

public class MyTagHandler implements TagHandler{
    boolean first= true;
    String parent=null;

    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {

        if(tag.equals("ul")) parent="ul";
        if(tag.equals("li")){
            if(parent.equals("ul")){
                if(first){
                    output.append("\n\t•");
                    first= false;
                }else{
                    first = true;
                }
            }
        }
        if(tag.equals("br")){
            output.append(("\n\t"));
        }
    }
}
