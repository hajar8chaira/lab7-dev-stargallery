package com.example.starsgallery.services;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> listStars;
    private static StarService uniqueInstance;

    private StarService() {
        // --- IL MANQUAIT CETTE LIGNE CI-DESSOUS ---
        listStars = new ArrayList<>();

        listStars.add(new Star("Emma Watson", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Emma_Watson_2013.jpg/220px-Emma_Watson_2013.jpg", 5f));
        listStars.add(new Star("Brad Pitt", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Brad_Pitt_2019_by_Glenn_Francis.jpg/220px-Brad_Pitt_2019_by_Glenn_Francis.jpg", 4.5f));
        listStars.add(new Star("Scarlett Johansson", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Scarlett_Johansson_by_Gage_Skidmore_2.jpg/220px-Scarlett_Johansson_by_Gage_Skidmore_2.jpg", 4.8f));
        listStars.add(new Star("Leonardo DiCaprio", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Leonardo_DiCaprio_2014.jpg/220px-Leonardo_DiCaprio_2014.jpg", 4.9f));
        listStars.add(new Star("George Clooney", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/George_Clooney_2016.jpg/220px-George_Clooney_2016.jpg", 4.2f));
        listStars.add(new Star("Angelina Jolie", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/Angelina_Jolie_2_CH_2015.jpg/220px-Angelina_Jolie_2_CH_2015.jpg", 4.7f));
        listStars.add(new Star("Johnny Depp", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Johnny_Depp-2739.jpg/220px-Johnny_Depp-2739.jpg", 4.6f));
        listStars.add(new Star("Natalie Portman", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Natalie_Portman_%2848470988352%29_%28cropped%29.jpg/220px-Natalie_Portman_%2848470988352%29_%28cropped%29.jpg", 4.8f));
    }

    public static StarService get() {
        if (uniqueInstance == null) uniqueInstance = new StarService();
        return uniqueInstance;
    }

    @Override public boolean add(Star o) { return listStars.add(o); }
    @Override public boolean update(Star o) { return true; }
    @Override public boolean remove(Star o) { return listStars.remove(o); }
    @Override public Star findById(int id) {
        for (Star s : listStars) if (s.getId() == id) return s;
        return null;
    }
    @Override public List<Star> getAll() { return listStars; }
}
