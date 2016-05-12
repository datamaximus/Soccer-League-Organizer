package com.teamtreehouse.model;

import java.util.Set;
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

    @Override
    public int compareTo(Team t) {
        return mName.compareTo(t.mName);
    }
}