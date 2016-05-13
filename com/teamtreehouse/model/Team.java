package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Team implements Comparable<Team> {
    public String mName;
    public String mCoach;
    public Set<Player> mPlayers;

    public Team(String name, String coach) {
        mName = name;
        mCoach = coach;
        mPlayers = new TreeSet<Player>();
    }

    public void addPlayer(Player player) {
        mPlayers.add(player);
    }

    public void removePlayer(Player player) {
        mPlayers.remove(player);
    }

    public Map<Integer, List<Player>> byHeight() {
        Map<Integer, List<Player>> teamByHeight = new TreeMap<Integer, List<Player>>();
        for (Player player : mPlayers) {
            List<Player> playersAtHeight = teamByHeight.get(player.getHeightInInches());
            if (playersAtHeight == null) {
                playersAtHeight = new ArrayList<>();
                teamByHeight.put(player.getHeightInInches(), playersAtHeight);
            }
            playersAtHeight.add(player);
        }
        return teamByHeight;
    }

    public Map<String, Integer> teamExperience() {
        Map<String, Integer> experiencedPlayers = new TreeMap<String, Integer>();
        int experiencedCount = 0;
        int inexperiencedCount = 0;
        for (Player player : mPlayers) {
            if (player.isPreviousExperience()) {
                experiencedCount++;
            } else {
                inexperiencedCount++;
            }
        }
        experiencedPlayers.put("Experienced", experiencedCount);
        experiencedPlayers.put("Inexperienced", inexperiencedCount);
        return experiencedPlayers;
    }

    public void displayRoster() {
        System.out.printf("%nRoster for %s%n", mName);
        System.out.printf("------------------%n");
        System.out.printf("Coach:%n%s%n%n", mCoach);
        System.out.println("Players:");
        for (Player player : mPlayers) {
            System.out.printf("%s %s%n", player.getFirstName(), player.getLastName());
        }
    }

    @Override
    public int compareTo(Team t) {
        return mName.compareTo(t.mName);
    }
}