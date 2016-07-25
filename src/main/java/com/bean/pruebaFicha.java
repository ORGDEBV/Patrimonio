package com.bean;

import com.entidad.Ejemplar;
import com.entidad.Marc001;
import com.entidad.Marc017;
import com.entidad.Marc100;
import com.entidad.Marc245;
import com.entidad.Marc250;
import com.entidad.Marc260;
import com.entidad.Marc300;
import com.entidad.Marc500;
import com.entidad.Marc504;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

public class pruebaFicha {

    public static void main(String[] args) throws FileNotFoundException {

        Marc001 marc001;
        Marc017 marc017;
        Marc100 marc100;
        Marc245 marc245;
        Marc250 marc250;
        Marc260 marc260;
        Marc300 marc300;
        Marc504 marc504;
        int Error = 0;
        InputStream in = new FileInputStream("C:\\Users\\virtual\\Desktop\\FormatoRaro\\xml-464812.xml");
        MarcReader reader = new MarcXmlReader(in);

        while (reader.hasNext()) {
            Record record = reader.next();

            ControlField campo001 = (ControlField) record.getVariableField("001");
            DataField campo17 = (DataField) record.getVariableField("017");
            DataField campo100 = (DataField) record.getVariableField("100");
            DataField campo245 = (DataField) record.getVariableField("245");
            DataField campo250 = (DataField) record.getVariableField("250");
            DataField campo260 = (DataField) record.getVariableField("260");
            DataField campo300 = (DataField) record.getVariableField("300");
            DataField campo490 = (DataField) record.getVariableField("490");
            List campo500 = record.getVariableFields("500");
            DataField campo504 = (DataField) record.getVariableField("504");
            List campo583 = record.getVariableFields("583");
            List campo852 = record.getVariableFields("852");

            marc001 = new Marc001();
            marc017 = new Marc017();
            marc100 = new Marc100();
            marc245 = new Marc245();
            marc250 = new Marc250();
            marc260 = new Marc260();
            marc300 = new Marc300();
            marc504 = new Marc504();
            List<Marc500> listaMarc500 = new ArrayList<>();
            List<Ejemplar> listaEjemplar = new ArrayList<>();

//            Leader leader = record.getLeader();
//            List controlfields = record.getControlFields();
//            List datafields = record.getDataFields();
//            System.out.println("LEADER DEL LIBRO : " + leader.toString());
//            System.out.println("CONTROL FIELDS");
//            for (int i = 0; i < controlfields.size(); i++) {
//                System.out.println("Campo #" + controlfields.get(i).toString().substring(0, 3) + " : " + controlfields.get(i).toString().substring(4));
//            }
//            System.out.println("DATA FIELDS");
//            for (int i = 0; i < datafields.size(); i++) {
//                System.out.println("Campo #" + datafields.get(i).toString().substring(0, 3) + " : " + datafields.get(i).toString().substring(4));
//            }
//            }}}
            System.out.println("*************************************************FICHA***********************************************************");
            if (campo100 != null) {
                Subfield subcampo100a = campo100.getSubfield('a');
                Subfield subcampo100d = campo100.getSubfield('d');
                if (subcampo100a != null) {
                    marc100.setA(subcampo100a.getData());
                    System.out.print("Campo 100 : " + marc100.getA());
                }
                if (subcampo100d != null) {
                    marc100.setD(subcampo100d.getData());
                    System.out.print(" " + marc100.getD());
                }
                System.out.println(" ");
            } else {
                System.out.print("Campo 100 : ");
                System.out.println("--");
            }
            if (campo245 != null) {
                Subfield subcampo245a = campo245.getSubfield('a');
                Subfield subcampo245b = campo245.getSubfield('b');
                Subfield subcampo245c = campo245.getSubfield('c');
                if (subcampo245a != null) {
                    marc245.setA(subcampo245a.getData());
                    System.out.print("Campo 245 : " + marc245.getA());
                }
                if (subcampo245b != null) {
                    marc245.setB(subcampo245b.getData());
                    System.out.print(" " + marc245.getB());
                }
                if (subcampo245c != null) {
                    marc245.setC(subcampo245c.getData());
                    System.out.print(" " + marc245.getC() + " --");
                }
                System.out.println(" ");
            } else {
                System.out.print("Campo 245 : ");
                System.out.println("--");
            }
            if (campo250 != null) {
                Subfield subcampo250a = campo250.getSubfield('a');
                if (subcampo250a != null) {
                    marc250.setA(subcampo250a.getData());
                    System.out.println("Campo 250 : " + marc250.getA() + " -- ");
                }
            } else {
                System.out.print("Campo 250 : ");
                System.out.println("--");
            }
            if (campo260 != null) {
                Subfield subcampo260a = campo260.getSubfield('a');
                Subfield subcampo260b = campo260.getSubfield('b');
                Subfield subcampo260c = campo260.getSubfield('c');
                if (subcampo260a != null) {
                    marc260.setA(subcampo260a.getData());
                    System.out.print("Campo 260 : " + marc260.getA());
                }
                if (subcampo260b != null) {
                    marc260.setB(subcampo260b.getData());
                    System.out.print(" " + marc260.getB());
                }
                if (subcampo260c != null) {
                    marc260.setC(subcampo260c.getData());
                    System.out.println(" " + marc260.getC() + " ");
                }
            } else {
                System.out.print("Campo 260 : ");
                System.out.println("--");
            }
            if (campo300 != null) {
                Subfield subcampo300a = campo300.getSubfield('a');
                Subfield subcampo300b = campo300.getSubfield('b');
                Subfield subcampo300c = campo300.getSubfield('c');
                if (subcampo300a != null) {
                    marc300.setA(subcampo300a.getData());
                    System.out.print("Campo 300 : " + marc300.getA());
                }
                if (subcampo300b != null) {
                    marc300.setB(subcampo300b.getData());
                    System.out.print(" " + marc300.getB());
                }
                if (subcampo300c != null) {
                    marc300.setC(subcampo300c.getData());
                    System.out.println(" " + marc300.getC());
                }
            } else {
                System.out.print("Campo 300 : ");
                System.out.println("--");
            }

            if (!campo500.isEmpty()) {
                Marc500 marc500 = new Marc500();
                for (int i = 0; i < campo500.size(); i++) {
                    DataField datox = (DataField) campo500.get(i);
                    List subcampos = datox.getSubfields();
                    Iterator it = subcampos.iterator();
                    System.out.print("Campo 500 : ");
                    while (it.hasNext()) {
                        Subfield subfield = (Subfield) it.next();
                        String code = String.valueOf(subfield.getCode());
                        String data = subfield.getData();
                        marc500.setA(data);
                        listaMarc500.add(marc500);
                        if (code.equals("a")) {
                            System.out.println(data);
                        }
                    }
                }
            }
            if (campo504 != null) {
                Subfield subcampo504a = campo504.getSubfield('a');
                if (subcampo504a != null) {
                    marc504.setA(subcampo504a.toString().substring(2));
                    System.out.println("Campo 504 : " + marc504.getA());
                }
            } else {
                System.out.print("Campo 504 : ");
                System.out.println("--");
            }
            if (campo17 != null) {
                Subfield subcampo17a = campo17.getSubfield('a');
                if (subcampo17a != null) {
                    marc017.setA(subcampo17a.getData());
                    System.out.println("Campo 017 : " + "D.L. " + marc017.getA());
                }
            } else {
                System.out.print("Campo 017 : ");
                System.out.println("--");
            }

            if (!campo583.isEmpty() && !campo852.isEmpty()) {
                Ejemplar ejemplar = new Ejemplar();
                if (campo583.size() < campo852.size()) {
                    System.out.println("Error en campo 583..Faltan agregar " + (campo852.size() - campo583.size()) + " registros");
                    Error = 1;
                } else {

                    for (int i = 0; i < campo852.size(); i++) {
                        DataField datoi = (DataField) campo852.get(i);
                        List subcampoi = datoi.getSubfields();

                        Iterator it = subcampoi.iterator();
                        while (it.hasNext()) {
                            Subfield subfield = (Subfield) it.next();
                            String code = String.valueOf(subfield.getCode());
                            String data = subfield.getData().trim();
                            switch (code) {
                                case "a":
                                    ejemplar.setA852(data);
                                    break;
                                case "b":
                                    ejemplar.setB852(data);
                                    break;
                                case "c":
                                    ejemplar.setC852(data);
                                    break;
                                case "h":
                                    ejemplar.setH852(data);
                                    break;
                                case "i":
                                    ejemplar.setI852(data);
                                    break;
                                case "j":
                                    ejemplar.setJ852(data);
                                    break;
                                case "p":
                                    ejemplar.setP852(data);
                                    break;
                                case "q":
                                    ejemplar.setQ852(data);
                                    break;
                                case "3":
                                    ejemplar.set852_3(data);
                                    break;
                            }
                        }
                        datoi = (DataField) campo583.get(i);
                        subcampoi = datoi.getSubfields();
                        it = subcampoi.iterator();
                        while (it.hasNext()) {
                            Subfield subfield = (Subfield) it.next();
                            String code = String.valueOf(subfield.getCode());
                            String data = subfield.getData().trim();
                            switch (code) {
                                case "a":
                                    ejemplar.setA583(data);
                                    break;
                                case "b":
                                    ejemplar.setB583(data);
                                    break;
                                case "c":
                                    ejemplar.setC583(data);
                                    break;
                                case "k":
                                    ejemplar.setK583(data);
                                    break;
                                case "x":
                                    ejemplar.setX583(data);
                                    break;
                            }
                        }
                        System.out.println(ejemplar.getA852()+"-"+ejemplar.getC852());
                        if (ejemplar.getA852().equals("BNP") && ejemplar.getC852().equals("Guillermo Lohmann Villena- Libros peruanos")) {
                            listaEjemplar.add(ejemplar);
                        }
                    }
                    System.out.println("hay " + listaEjemplar.size() + " registros en BNP... de " + campo852.size() + " registros TOTALES");
                }
            } else {
                System.out.print("Campo ejemplar : ");
                System.out.println("--");
            }
            if (campo001 != null) {
                System.out.println("MFN = " + campo001.getData().substring(1));
            } else {
                System.out.print("MFN =");
                System.out.println("--");
            }
            //si error =1 mostrar
            System.out.println("*********************************************FIN-FICHA***********************************************************");
            //grabarficha(); 
        }

    }

}
