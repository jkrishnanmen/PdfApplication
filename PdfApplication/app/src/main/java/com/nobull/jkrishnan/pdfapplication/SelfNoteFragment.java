package com.nobull.jkrishnan.pdfapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by empressum on 21/11/16.
 */

public class SelfNoteFragment extends Fragment {

    private View mRootView;
    private EditText mSubjectEditText,mBodyEditText;
    private Button mSaveButton;
    Context context;
    PrintAttributes printAttributes;
    View view;

    public SelfNoteFragment(){

    }

    public static SelfNoteFragment newInstance(){
        SelfNoteFragment fragment=new SelfNoteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView=inflater.inflate(R.layout.fragment_self_note,container,false);
        mSubjectEditText=(EditText) mRootView.findViewById(R.id.edit_text_subject);
        mBodyEditText=(EditText) mRootView.findViewById(R.id.edit_text_body);
        mSaveButton=(Button) mRootView.findViewById(R.id.button_save);
        context=getContext();
        try{
            createPdf();

        }catch(FileNotFoundException e){

        }




        return mRootView;
    }

    private void createPdf() throws FileNotFoundException{

        File pdfFolder=new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS),"pdfdemo");
        if(!pdfFolder.exists()){
            pdfFolder.mkdir();
            Log.d("SelfNoteFragment"," Pdf Created");
        }
        Date date = new Date();
        String timeStamp=new SimpleDateFormat("yyyMMdd_HHmmss").format(date);
        File myFile= new File(pdfFolder+timeStamp+".pdf");
        OutputStream outputStream=new FileOutputStream(myFile);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height=displayMetrics.heightPixels,
                width=displayMetrics.widthPixels;
        Rectangle rectangle=new Rectangle(height,width);


        //Creating a Pdf Document
//        PdfDocument pdfDocument= new PdfDocument();

        com.itextpdf.text.Document iTxtPdfDoc = new com.itextpdf.text.Document(rectangle);
        try{
            PdfWriter.getInstance(iTxtPdfDoc,outputStream);
            iTxtPdfDoc.open();
            Boolean ans=addContent(iTxtPdfDoc);
            Log.d("pdfshit",ans.toString());
            iTxtPdfDoc.close();
            outputStream.close();

        } catch (DocumentException e){

        } catch (IOException e){

        }


//        File file=new File("temppdf"+date.toString()+timeStamp);
//        if(file.exists()){
//
//        }
//        PdfDocument.PageInfo pageInfo= new PdfDocument.PageInfo.Builder(width,height,4).create();
//        PdfDocument.Page page=pdfDocument.startPage(pageInfo);
//        PdfPTable name= new PdfPTable(2);
//        mRootView.draw(page.getCanvas());
//        pdfDocument.finishPage(page);
//        try{
//            pdfDocument.writeTo(outputStream);
//
//        }catch (IOException e){
//
//        }




    }

    public boolean addContent(com.itextpdf.text.Document document){
        boolean done=false;
        try{
            document.addTitle("Dummy Text Document");
            Paragraph paragraph=new Paragraph();
            paragraph=new Paragraph("this is dummy paragraph do not worry");
            document.add(paragraph);
            document.add(new LineSeparator());
            done =true;


        } catch (DocumentException e){
            done=false;

        }
        return done;

    }

}
