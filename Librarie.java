import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class Librarie extends JFrame{
    ArrayList<Carte> listaCarti = new ArrayList<>();
    File fisier = new File("carti.txt");
    JList<Carte> lista = new JList<>();
    DefaultListModel<Carte> model = new DefaultListModel<>();
    JScrollPane scroll;
    JButton adauga, actualizare, sortareAutor, sortareExemplare, filtrare;
    JTextField tip, titlu, autor, numarDisponibil, tipFiltrare, tipActualizare, titluActualizare, autorActualizare, numarDisponibilActualizare;
    JLabel sortare, tipL, titluL, autorL, numarDisponibilL, tipActualizareL, titluActualizareL, autorActualizareL, numarDisponibilActualizareL;
    JPanel stanga, centru, dreapta, jos;

    Librarie() {
        setTitle("Librarie");
        setSize(900, 630);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setResizable(false);

        boolean fisierGol = fisier.exists() && (fisier.length() <= 0);
        if(!fisier.exists()) {
            try {
                fisier.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(!fisierGol) {
            deserializare();

            model.clear();
            for(Carte c:listaCarti)
                model.addElement(c);
        }

        stanga = new JPanel();
        stanga.setPreferredSize(new Dimension(270, 300));
        stanga.setVisible(true);
        stanga.setLayout(new FlowLayout());

        centru = new JPanel();
        centru.setPreferredSize(new Dimension(270, 300));
        centru.setVisible(true);
        centru.setLayout(new FlowLayout());

        dreapta = new JPanel();
        dreapta.setPreferredSize(new Dimension(270, 300));
        dreapta.setVisible(true);
        dreapta.setLayout(new FlowLayout());

        jos = new JPanel();
        jos.setPreferredSize(new Dimension(550, 290));
        jos.setVisible(true);

        scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(550, 250));
        lista.setModel(model);

        tipL = new JLabel();
        tipL.setText("Tip");
        tipL.setPreferredSize(new Dimension(190, 25));
        tipL.setVisible(true);

        tip = new JTextField();
        tip.setPreferredSize(new Dimension(190, 25));

        titluL = new JLabel();
        titluL.setText("Titlu");
        titluL.setPreferredSize(new Dimension(190, 25));
        titluL.setVisible(true);

        titlu = new JTextField();
        titlu.setPreferredSize(new Dimension(190, 25));

        autorL = new JLabel();
        autorL.setText("Autor");
        autorL.setPreferredSize(new Dimension(190, 25));
        autorL.setVisible(true);

        autor = new JTextField();
        autor.setPreferredSize(new Dimension(190, 25));

        numarDisponibilL = new JLabel();
        numarDisponibilL.setText("Numar disponibil");
        numarDisponibilL.setPreferredSize(new Dimension(190, 25));
        numarDisponibilL.setVisible(true);

        numarDisponibil = new JTextField();
        numarDisponibil.setPreferredSize(new Dimension(190, 25));

        tipActualizareL = new JLabel();
        tipActualizareL.setText("Tip");
        tipActualizareL.setPreferredSize(new Dimension(190, 25));
        tipActualizareL.setVisible(true);

        tipActualizare = new JTextField();
        tipActualizare.setPreferredSize(new Dimension(190, 25));

        titluActualizareL = new JLabel();
        titluActualizareL.setText("Titlu");
        titluActualizareL.setPreferredSize(new Dimension(190, 25));
        titluActualizareL.setVisible(true);

        titluActualizare = new JTextField();
        titluActualizare.setPreferredSize(new Dimension(190, 25));

        autorActualizareL = new JLabel();
        autorActualizareL.setText("Autor");
        autorActualizareL.setPreferredSize(new Dimension(190, 25));
        autorActualizareL.setVisible(true);

        autorActualizare = new JTextField();
        autorActualizare.setPreferredSize(new Dimension(190, 25));

        numarDisponibilActualizareL = new JLabel();
        numarDisponibilActualizareL.setText("Numar disponibil");
        numarDisponibilActualizareL.setPreferredSize(new Dimension(190, 25));
        numarDisponibilActualizareL.setVisible(true);

        numarDisponibilActualizare = new JTextField();
        numarDisponibilActualizare.setPreferredSize(new Dimension(190, 25));

        adauga = new JButton("Adauga");
        adauga.setPreferredSize(new Dimension(190, 25));
        adauga.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sTip = tip.getText();
                String sTitlu = titlu.getText();
                String sAutor = autor.getText();
                String sNumarDisponibil = numarDisponibil.getText();

                Carte carte = new Carte(sTip, sTitlu, sAutor, Integer.parseInt(sNumarDisponibil));
                if(!sTip.equals("") && !sTitlu.equals("") && !sAutor.equals("") && !sNumarDisponibil.equals("")) {
                    listaCarti.add(carte);
                    model.addElement(carte);

                    tip.setText("");
                    titlu.setText("");
                    autor.setText("");
                    numarDisponibil.setText("");
                    serializare();
                }
            }

        });

        actualizare = new JButton("Actualizare");
        actualizare.setPreferredSize(new Dimension(190, 25));
        actualizare.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sTip = tip.getText();
                String sTitlu = titlu.getText();
                String sAutor = autor.getText();
                String sNumarDisponibil = numarDisponibil.getText();

                String sTipA = tipActualizare.getText();
                String sTitluA = titluActualizare.getText();
                String sAutorA = autorActualizare.getText();
                String sNumarDisponibilA = numarDisponibilActualizare.getText();

                if((!sTip.equals("") && !sTitlu.equals("") && !sAutor.equals("") && !sNumarDisponibil.equals(""))) {
                    model.clear();
                    int index = -1;
                    for(Carte c:listaCarti) {
                        if(sTip.equals(c.getTip()) && sTitlu.equals(c.getTitlu()) && sAutor.equals(c.getAutor()) && sNumarDisponibil.equals(Integer.toString(c.getNumarDisponibil()))) {
                            if(sTipA.equals("") && sTitluA.equals("") && sAutorA.equals("") && sNumarDisponibilA.equals("")) {
                               index = listaCarti.indexOf(c);
                            } else {
                                if(!sTipA.equals(""))
                                    c.setTip(sTipA);
                                if(!sTitluA.equals(""))
                                    c.setTitlu(sTitluA);
                                if(!sAutorA.equals(""))
                                    c.setAutor(sAutorA);
                                if(!sNumarDisponibilA.equals(""))
                                    c.setNumarDisponibil(Integer.parseInt(sNumarDisponibilA));
                            }
                        }

                        if(index == -1)
                            model.addElement(c);
                    }

                    tip.setText("");
                    titlu.setText("");
                    autor.setText("");
                    numarDisponibil.setText("");

                    tipActualizare.setText("");
                    titluActualizare.setText("");
                    autorActualizare.setText("");
                    numarDisponibilActualizare.setText("");

                    if(index != -1)
                        listaCarti.remove(index);
                    for(Carte c:listaCarti) {
                        model.addElement(c);
                    }
                    serializare();
                }
            }

        });

        sortare = new JLabel();
        sortare.setText("Sortare/Filtrare");
        sortare.setPreferredSize(new Dimension(190, 25));
        sortare.setVisible(true);

        sortareAutor = new JButton("Sortare dupa autor");
        sortareAutor.setPreferredSize(new Dimension(190, 25));
        sortareAutor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                Comparator<Carte> com = new Comparator<Carte>() {
                    @Override
                    public int compare(Carte o1, Carte o2) {
                        if(o1.getAutor().toLowerCase().compareTo(o2.getAutor().toLowerCase()) > 0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                };

                lista.clearSelection();
                Collections.sort(listaCarti, com);
                model.clear();
                for(Carte c:listaCarti) {
                    model.addElement(c);
                }
            }

        });

        sortareExemplare = new JButton("Sortare dupa numar carti");
        sortareExemplare.setPreferredSize(new Dimension(190, 25));
        sortareExemplare.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                Comparator<Carte> com = new Comparator<Carte>() {
                    @Override
                    public int compare(Carte o1, Carte o2) {
                        if(o1.getNumarDisponibil() > o2.getNumarDisponibil()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                };

                Collections.sort(listaCarti, com);
                model.clear();
                for(Carte c:listaCarti) {
                    model.addElement(c);
                }
            }

        });

        tipFiltrare = new JTextField();
        tipFiltrare.setText("Introduceti tip");
        tipFiltrare.setPreferredSize(new Dimension(190, 25));

        filtrare = new JButton("Filtrare dupa tip");
        filtrare.setPreferredSize(new Dimension(190, 25));
        filtrare.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sTip = tipFiltrare.getText();

                if(sTip.equals("")) {
                    model.clear();
                    for(Carte c:listaCarti)
                        model.addElement(c);
                } else {
                    model.clear();
                    for(Carte c:listaCarti) {
                        if(c.getTip().equals(sTip))
                            model.addElement(c);
                    }
                }
            }

        });

        stanga.add(tipL);
        stanga.add(tip);
        stanga.add(titluL);
        stanga.add(titlu);
        stanga.add(autorL);
        stanga.add(autor);
        stanga.add(numarDisponibilL);
        stanga.add(numarDisponibil);
        stanga.add(adauga);

        centru.add(tipActualizareL);
        centru.add(tipActualizare);
        centru.add(titluActualizareL);
        centru.add(titluActualizare);
        centru.add(autorActualizareL);
        centru.add(autorActualizare);
        centru.add(numarDisponibilActualizareL);
        centru.add(numarDisponibilActualizare);
        centru.add(actualizare);

        dreapta.add(sortare);
        dreapta.add(sortareAutor);
        dreapta.add(sortareExemplare);
        dreapta.add(tipFiltrare);
        dreapta.add(filtrare);

        jos.add(scroll);

        add(stanga);
        add(centru);
        add(dreapta);
        add(jos);
        setVisible(true);
    }

    private void serializare() {
        try {
            FileOutputStream fos = new FileOutputStream(fisier);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(listaCarti);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deserializare() {
        try {
            FileInputStream fis = new FileInputStream(fisier);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Carte> l=new ArrayList<>();

            List<?> o=(List<?>) ois.readObject();
            for(Object i:o)
                l.add((Carte) i);
            for(Carte c:l)
                listaCarti.add(c);
            ois.close();
            fis.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
