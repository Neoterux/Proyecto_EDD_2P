package com.neoterux.sttkoe.utils;

import com.neoterux.sttkoe.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * This utils class would contain helper methods for handle JavaFx Application things
 */
public final class AppUtils {
    private AppUtils() {
    }

    private static final Logger log = LoggerFactory.getLogger(AppUtils.class);

    /**
     * This method would get a FXMLoader from a root class.
     *
     * @param rootClassPackage this class would be the root path to load a fxml.
     * @param relLoc           the relative location of an FXML file from the specified {@literal rootClassPackage}.
     *                         Don't put .fxml to the end of the string.
     * @return The FXML loader, null if the location is bad
     * @
     */
    public static FXMLLoader loaderFrom(final Class<?> rootClassPackage, String relLoc) {
        log.debug("Fetching FXML Loader from location: " + rootClassPackage.getResource(relLoc));
        var rt = rootClassPackage.getResource(relLoc);
        if (rt == null) {
            log.error("Relative location is invalid, input: " + relLoc);
            return null;
        }
        return loaderFrom(rt);
    }

    /**
     * This method would get a FXMLoader from a specified location.
     *
     * @param relLoc the location of the target fxml. from the {@link com.neoterux.sttkoe.App} class.
     * @return the FXML loader, null if the location is invalid.
     */
    public static FXMLLoader loaderFrom(String relLoc) {
        return loaderFrom(App.class, relLoc);
    }

    /**
     * This method would get a FXML Loader from a specified URL
     *
     * @param loc the url of the fxml file.
     * @return a FxmlLoader.
     */
    public static FXMLLoader loaderFrom(URL loc) {
        log.debug("loading from URL: " + loc);
        return new FXMLLoader(loc);
    }

    public static Parent loadParent(String fxmlPath) {
        log.info("loading file: " + fxmlPath);
        FXMLLoader loader = loaderFrom(fxmlPath);
        Parent root = null;
        try {
            root = loader.load();
        } catch (NullPointerException npe) {
            log.error("Cannot parse fxml, the loader is null!");
        } catch (IOException ioe) {
            log.error("An io error occurred when reading the fxml.", ioe);
        }
        return root;
    }

    public static Parent loadParent(Class<?> root, String relLoc) {
        var res = loaderFrom(root, relLoc);
        if (res == null) {
            log.error("The relative location is not valid.");
            return null;
        }
        Parent rootParent = null;
        try {
            rootParent = res.load();
        } catch (NullPointerException npe) {
            log.error("Cannot parse fxml, the loader is null!");
        } catch (IOException ioe) {
            log.error("An io error occurred when reading the fxml.", ioe);
        }
        return rootParent;
    }

}
