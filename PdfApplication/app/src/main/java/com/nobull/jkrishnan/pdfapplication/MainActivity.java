package com.nobull.jkrishnan.pdfapplication;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelfNoteFragment noteFragment=SelfNoteFragment.newInstance();

        android.support.v4.app.FragmentTransaction fragmentTransaction= this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,noteFragment);
        fragmentTransaction.commit();

    }

    public void createandDisplayPdf(String text){

        PdfDocument document=new PdfDocument();
        PdfDocument.PageInfo pageInfo= new PdfDocument.PageInfo.Builder(100,100,10).create();

        PdfDocument.Page page=document.startPage(pageInfo);

//        View content = content.draw(page.getCanvas());



        document.finishPage(page);
    }

    public void pdfwriterClass(){



    }
}
