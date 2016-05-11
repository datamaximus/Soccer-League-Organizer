package com.teamtreehouse.model;

import java.util.Set;

public class Team {
    public String mName;
    public String mCoach;
    public Set<Player> mPlayers;

    public Team(String name, String coach) {
        mName = name;
        mCoach = coach;
    }
}