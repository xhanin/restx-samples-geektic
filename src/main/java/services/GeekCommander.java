package services;

import geeks.Geek;
import resources.SearchResource;

import javax.inject.Inject;

/**
 * User: xavierhanin
 * Date: 3/23/13
 * Time: 1:42 PM
 */
public class GeekCommander {
    private SearchResource searchResource;

    @Inject
    public GeekCommander(SearchResource searchResource) {
        this.searchResource = searchResource;
    }

    public GeekCommand parse(String name, String status) {
        Geek geek = new Geek();
        geek.prenom = name;
        geek.nom = "";

        int i = name.indexOf(" ");
        if (i != -1) {
            geek.prenom = name.substring(0, i).trim();
            geek.nom = name.substring(i + 1).trim();
        }

        geek.like1 = "test";

        return new GeekCommand(searchResource, geek);
    }

    public static class GeekCommand implements Runnable {
        private final SearchResource searchResource;
        private final Geek geek;

        public GeekCommand(SearchResource searchResource, Geek geek) {
            this.searchResource = searchResource;
            this.geek = geek;
        }

        public Geek getGeek() {
            return geek;
        }

        @Override
        public void run() {
            searchResource.addGeek(geek);
        }
    }


}