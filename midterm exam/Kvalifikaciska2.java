

Да се ​​дефинира класата SkillsMatcher за евалуација на IT кандидатите според бараните вештини. Класите да ги имаат следните функции:

Конструктор skillsMatcher (List<String> requiredSkills)

кој прима листа од бараните вештини за одредена работна позиција.

Метод public void evaluateCandidates (InputStream is, itputStream os)

Кој од вреден поток е потребен да ги прочита информациите за кандидатите кои апликанти за позиционирање, секој во посебен ред во формат: CANDIDATE ID SKILLI SKILLI2 - SKILL каде CANDIDATE ID е идентификатор на кандидатот, а SKILLI претставува вештина што кандидатот ја поседува Методот треба да се користи за да се испечатат кандидатите и бројот на потребни вештини кои тие ги поседуваат, а кои се барани за позиционирање, подредени во опожачки редослед според бројот на потребни вештини.

Дефинирајте класа SkillsMatcher за оценување на ИТ кандидати врз основа на потребни вештини. Класата треба да ги има следниве функционалности:

Конструктор skillsMatcher (List<String> requiredSkills) кој прифаќа листа на потребни ИТ вештини за одредена работна позиција.

Метод public void evaluateCandidates (InputStream is, OutputStream os)

Од влезниот поток треба да се прочитаат информациите за кандидатите кои аплицираат за позицијата, секоја дадена во посебен ред во формат: CANDIDATE_ID SKILLI SKILL2-SKILLn каде што CANDIDATE_ID е идентификаторот на кандидатот, а SKILLi претставува вештина што ја поседува кандидатот. Методот треба да ги испечати во излезниот поток кандидатите и бројот на потребни вештини што ги поседуваат, сортирани во опаѓачки редослед врз основа на бројот на совпаднати (задолжителни) вештини.

На пример:

Влез

Резултат

java; docker; spring; git

C3 4

C1 java c++ git

C1 2

C2 1

C2 python docker flask

java spring git docker C40

C3

C4 html css javascript



package Naprendo;

import java.io.*;
import java.util.*;

public class SkillsMatcher {

    private List<String> requiredSkills;

    public SkillsMatcher(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void evaluateCandidates(InputStream is, OutputStream os) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

        List<Candidate> candidates = new ArrayList<>();

        String line;
        try {
            while (true) {

                line = br.readLine();
                if (line == null) break;
                if (line.trim().isEmpty()) break;

                String[] parts = line.split("\\s+");
                String id = parts[0];

                List<String> skills = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    skills.add(parts[i]);
                }

                int matches = 0;
                for (String rs : requiredSkills) {
                    if (skills.contains(rs)) {
                        matches++;
                    }
                }

                candidates.add(new Candidate(id, matches));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        candidates.sort((a, b) -> Integer.compare(b.matches, a.matches));

        for (Candidate c : candidates) {
            pw.println(c.id + " " + c.matches);
        }

        pw.flush();
    }

    private static class Candidate {
        String id;
        int matches;

        Candidate(String id, int matches) {
            this.id = id;
            this.matches = matches;
        }
    }

    public static void main(String[] args) {
        List<String> required = Arrays.asList("Java", "SQL", "Spring");

        SkillsMatcher matcher = new SkillsMatcher(required);
        matcher.evaluateCandidates(System.in, System.out);
    }
}







  
