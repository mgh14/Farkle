package properties;

import org.testng.annotations.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

import static org.testng.Assert.fail;

public class XMLHelperTest {

    private XMLHelper xmlHelper = new XMLHelper();


    private File createFile(String filename, String content) throws Exception {
        XMLInputFactory iff = XMLInputFactory.newFactory();

        XMLStreamReader reader = iff.createXMLStreamReader(new FileInputStream(new File(PropertiesManager.translateFilepathToAbsoluteFilepath(filename + ".xml"))));

        reader.next();
        while(reader.hasNext() && !reader.isStartElement() && reader.getName().equals("setting"))
            reader.next();


        reader.next();
        while(reader.hasNext() && !reader.isStartElement()) {

            System.out.println("getattr1: " + reader.getAttributeValue(0));
            System.out.println("getattr2: " + reader.getAttributeValue(1));
        }


        //while(reader.getText().equals(""))
        //    reader.next();

        //while(reader.hasNext() && !reader.getText().equals("name"))
        //    reader.next();
        /*while(reader.hasNext()) {
            //System.out.println("h: " + reader.getAttributeValue());
            int mine = reader.next();
            System.out.println(mine);


            if(!reader.isStartElement()) {
                continue;
            }

            while(reader.isStartElement())
                mine = reader.next();

            if(mine == reader.CHARACTERS && !reader.getText().trim().equals("")) {
                System.out.println("text: " + reader.getText().trim());

                while(!reader.isStartElement())
                    reader.next();

                while(reader.isStartElement())
                    mine = reader.next();

                if(mine == reader.CHARACTERS && !reader.getText().trim().equals(""))
                    System.out.println("again: " + reader.getText().trim());

            }


        }*/

        fail();

        /*File fileToWrite = new File(PropertiesManager.translateFilepathToAbsoluteFilepath(filename + "xml"));
        StringWriter mine =new StringWriter(new File(PropertiesManager.translateFilepathToAbsoluteFilepath(filename + "xml")));
        mine.write("hello");
        //File myFile = new File(PropertiesManager.translateFilepathToAbsoluteFilepath(filename + "xml"));
        //OutputStream mien = new FileOutputStream(new File(PropertiesManager.translateFilepathToAbsoluteFilepath(filename + "xml")))

        //        mien.write

        //return mien;*/

                return null;

    }

    @Test
    public void parseRestrictedPermissionsProfile() throws Exception {
        String profileName = "profileName.xml";
        createFile("/profiles/4playerProfile","");
        //create profile
        //

        //xmlHelper.parseProfile();
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void parseNonexistingProfile() throws Exception {
        xmlHelper.parseProfile("bogus profile");
    }

    @Test
    public void parseProfileWithRelativeFilepath() throws Exception {
        //create profile
        //

        //xmlHelper.parseProfile
    }

    @Test
    public void parseProfileWithAbsoluteFilepath() throws Exception {

    }

    @Test
    public void parseInvalidXMLProfile() throws Exception {
        // create profile
    }

    @Test
    public void parseProfileValidXMLProfile() throws Exception {

    }
}
