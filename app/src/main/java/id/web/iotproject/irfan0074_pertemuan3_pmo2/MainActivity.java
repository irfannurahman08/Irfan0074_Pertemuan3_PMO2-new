package id.web.iotproject.irfan0074_pertemuan3_pmo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mNama = new ArrayList<>();
    ArrayList<String> mPosisi = new ArrayList<>();
    ArrayList<String> mClub = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream istream = getAssets().open("userdata.xml");
            DocumentBuilderFactory builderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(istream);
            NodeList nList = doc.getElementsByTagName("user");

            for (int i = 0;i <nList.getLength(); i++ ){
                if (nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                    Element elm = (Element) nList.item(i);
                    mNama.add(getNodeValue("nama",elm));
                    mPosisi.add(getNodeValue("posisi",elm));
                    mClub.add(getNodeValue("club",elm));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Eror :"+ e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        ListDataAdapter adapter = new ListDataAdapter(this, mNama, mPosisi, mClub);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private String getNodeValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        Node node = nodeList.item(0);
        if (node != null){
            if (node.hasChildNodes()){
                Node child = node.getFirstChild();
                while (child != null){
                    if (child.getNodeType()== node.TEXT_NODE){
                        return  child.getNodeValue();

                    }
                }
            }
        }
        return "";
    }
}