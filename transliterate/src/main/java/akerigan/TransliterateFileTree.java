package akerigan;

import akerigan.util.StringUtils;
import akerigan.util.file.FileLister;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Vinichenko
 * @since 2012-05-17 18:21
 */
public class TransliterateFileTree {

    public static final Map<Character, String> RU_TO_EN;

    static {
        RU_TO_EN = new HashMap<Character, String>();

        RU_TO_EN.put('А', "A");
        RU_TO_EN.put('Б', "B");
        RU_TO_EN.put('В', "V");
        RU_TO_EN.put('Г', "G");
        RU_TO_EN.put('Д', "D");
        RU_TO_EN.put('Е', "E");
        RU_TO_EN.put('Ё', "Yo");
        RU_TO_EN.put('Ж', "Zh");
        RU_TO_EN.put('З', "Z");
        RU_TO_EN.put('И', "I");
        RU_TO_EN.put('Й', "J");
        RU_TO_EN.put('К', "K");
        RU_TO_EN.put('Л', "L");
        RU_TO_EN.put('М', "M");
        RU_TO_EN.put('Н', "N");
        RU_TO_EN.put('О', "O");
        RU_TO_EN.put('П', "P");
        RU_TO_EN.put('Р', "R");
        RU_TO_EN.put('С', "S");
        RU_TO_EN.put('Т', "T");
        RU_TO_EN.put('У', "U");
        RU_TO_EN.put('Ф', "F");
        RU_TO_EN.put('Х', "H");
        RU_TO_EN.put('Ц', "C");
        RU_TO_EN.put('Ч', "Ch");
        RU_TO_EN.put('Ш', "Sh");
        RU_TO_EN.put('Щ', "Sch");
        RU_TO_EN.put('Ъ', "'");
        RU_TO_EN.put('Ы', "Y");
        RU_TO_EN.put('Ь', "'");
        RU_TO_EN.put('Э', "`E");
        RU_TO_EN.put('Ю', "Yu");
        RU_TO_EN.put('Я', "Ya");

        RU_TO_EN.put('а', "a");
        RU_TO_EN.put('б', "b");
        RU_TO_EN.put('в', "v");
        RU_TO_EN.put('г', "g");
        RU_TO_EN.put('д', "d");
        RU_TO_EN.put('е', "e");
        RU_TO_EN.put('ё', "yo");
        RU_TO_EN.put('ж', "zh");
        RU_TO_EN.put('з', "z");
        RU_TO_EN.put('и', "i");
        RU_TO_EN.put('й', "j");
        RU_TO_EN.put('к', "k");
        RU_TO_EN.put('л', "l");
        RU_TO_EN.put('м', "m");
        RU_TO_EN.put('н', "n");
        RU_TO_EN.put('о', "o");
        RU_TO_EN.put('п', "p");
        RU_TO_EN.put('р', "r");
        RU_TO_EN.put('с', "s");
        RU_TO_EN.put('т', "t");
        RU_TO_EN.put('у', "u");
        RU_TO_EN.put('ф', "f");
        RU_TO_EN.put('х', "h");
        RU_TO_EN.put('ц', "c");
        RU_TO_EN.put('ч', "ch");
        RU_TO_EN.put('ш', "sh");
        RU_TO_EN.put('щ', "sch");
        RU_TO_EN.put('ъ', "'");
        RU_TO_EN.put('ы', "y");
        RU_TO_EN.put('ь', "'");
        RU_TO_EN.put('э', "`e");
        RU_TO_EN.put('ю', "yu");
        RU_TO_EN.put('я', "ya");
    }

    public static void main(String[] args) {
//        String startDir = "C:\\work\\projects\\1\\geps";
        String startDir = "C:\\work\\projects\\1\\geps\\branches\\geps-prod";
        FileLister fileLister = new FileLister();
        fileLister.addStartDir(new File(startDir));
        ArrayList<File> dirs = fileLister.findDirs(false);
        for (File dir : dirs) {
            transliterate(dir);
        }

        List<File> files = fileLister.findFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return true;
            }
        });

        for (File file : files) {
            transliterate(file);
        }
    }

    private static void transliterate(File file) {
        String name = file.getName();
        String newName = transliterate(name);
        if (!newName.equals(name)) {
            File parentFile = file.getParentFile();
            File newFile = new File(parentFile, newName);
            if (file.renameTo(newFile)) {
                System.out.println(
                        "+ " + file.getAbsolutePath() + " -> " + newFile.getAbsolutePath()
                );
            } else {
                System.out.println(
                        "- " + file.getAbsolutePath() + " -> " + newFile.getAbsolutePath()
                );
            }
        }
    }

    public static String transliterate(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0, length = s.length(); i < length; i += 1) {
            char ch = s.charAt(i);
            String tr = RU_TO_EN.get(ch);
            if (tr != null) {
                builder.append(tr);
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

}
