package org.isatools.webvalley.bioimage;

import au.com.bytecode.opencsv.CSVReader;
import org.isatools.isacreator.ontologymanager.bioportal.model.AnnotatorResult;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 29/06/2012
 *         Time: 16:03
 */
public class CellProfilerCSVReader {


    private String[] columnNames;
    private final List<String[]> values = new ArrayList<String[]>();

    public void readCellProfilerFile(String fileName) throws FileNotFoundException {

        CSVReader csvReader = new CSVReader(new FileReader(fileName));

        String[] nextLine;
        try {
            int count = 0;
            while ((nextLine = csvReader.readNext()) != null) {
                if (count == 0) {
                    columnNames = nextLine;
                } else {
                    values.add(nextLine);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public static void main(String[] args) {
        CellProfilerCSVReader csvReader = new CellProfilerCSVReader();

        try {
            csvReader.readCellProfilerFile("Data/DefaultOUT_Cells.csv");

            Set<String> columnNames = new HashSet<String>();

            for (String columnName : csvReader.getColumnNames()) {
                String value = StringUtils.processString(columnName);
                System.out.println(value);
                columnNames.add(value);

            }

            AnnotationTagger tagger = new AnnotationTagger();
            Map<String, Map<String, AnnotatorResult>> freeTextTermToMatches = tagger.annotateTerms(columnNames);

            for (String freeText : freeTextTermToMatches.keySet()) {
                System.out.println(freeText);

                for (String ontologyId : freeTextTermToMatches.get(freeText).keySet()) {
                    AnnotatorResult annotatorResult = freeTextTermToMatches.get(freeText).get(ontologyId);
                    System.out.println("\t" + annotatorResult.getOntologyTerm().getOntologyTermName() + " (" +
                            annotatorResult.getOntologySource().getOntologyAbbreviation() + ")");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
