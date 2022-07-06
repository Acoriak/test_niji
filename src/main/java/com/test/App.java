package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    private static class Party implements Comparable<Party> {
        private String name;
        private int votes;

        public Party() {
            name = "";
            votes = 0;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String newName) {
            this.name = newName;
        }

        public int getVotes() {
            return this.votes;
        }

        public void setVotes(int newVotes) {
            this.votes = newVotes;
        }

        @Override
        public int compareTo(Party party) {
            if (this.votes > party.votes) {
                return 1;
            } else if (this.votes == party.votes) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main( String[] args )
    {
        ArrayList<String> res = new ArrayList<>();
        try {
            File file = new File("data.csv");
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            List<String> lines = bReader.lines().toList();
            bReader.close();
            for (String line : lines) {
                printLine(line);

                ArrayList<Party> parties = new ArrayList<>();
                
                int totalVotes = 0;
                
                String[] sep = line.split(",");

                String string = sep[0] + " : ";

                int i = 1;
                while (i < sep.length) {
                    totalVotes += Integer.parseInt(sep[i].substring(1));

                    Party party = new Party();
                    party.votes = Integer.parseInt(sep[i].substring(1));
                    switch (sep[i+1].substring(1)) {
                        case "C":
                            party.name = "Conservative Party";
                            break;
                        case "L":
                            party.name = "Labour Party";
                            break;
                        case "LD":;
                            party.name = "Liberal Democrats";
                            break;
                        case "G":
                            party.name = "Green Party";
                            break;
                        case "Ind":
                            party.name = "Independent";
                            break;
                        default:
                            party.name = sep[i+1].substring(1);
                            break;
                    }
                    parties.add(party);
                    i += 2;
                }
                Collections.sort(parties);
                Collections.reverse(parties);
                for (Party party : parties) {
                    string += party.name + " : " + (party.votes * 100 / totalVotes) + "% | ";
                }
                res.add(string);
            };

            // Sortie dans le fichier output.csv et dans la console
            File outputFile = new File("output.csv");
            FileWriter fWriter = new FileWriter(outputFile);

            for (String string : res) {
                printLine(string);
                fWriter.write(string + "\n");
            }
            fWriter.close();
            outputFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printLine(String string) {
        System.out.println(string);
    }
}
