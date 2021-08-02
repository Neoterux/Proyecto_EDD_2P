package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.custom.PlayerMark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>R class</h1>
 * This class only contain Resources or settings of the application.
 */
public final class R {

    private static final Logger log = LoggerFactory.getLogger(R.class);

    public static final String MARK_PATH = "game/mark";

    private static final File MARK_DIR = new File(MARK_PATH);

    private static final FilenameFilter MARK_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().strip().endsWith(".mark");
        }
    };

    public static List<PlayerMark> readMarks() {
        List<PlayerMark> marks = new ArrayList<>();
        log.info("Reading saved marks at " + MARK_PATH);

        File[] files = MARK_DIR.listFiles(MARK_FILTER);
        if (files != null)
            for (File f : files) {

                try (var rd = new ObjectInputStream(new FileInputStream(f))) {

                    marks.add((PlayerMark) rd.readObject());

                } catch (ClassNotFoundException cnf) {
                    log.error("Invalid .mark file", cnf);
                } catch (StreamCorruptedException s) {
                } catch (IOException ioe) {
                    log.error("An io exception occurred while reading.", ioe);
                } catch (NullPointerException npe) {
                    log.error("An unknown NullPointerException caught: ", npe);
                } catch (Exception e) {
                    log.error("Unknown error occurred, see stackTrace: ", e);
                }
            }
        else {
            MARK_DIR.mkdirs();
        }
        return marks;
    }


    private R() {
    }
}
