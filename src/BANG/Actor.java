package BANG;

/**
 * This abstract class is used for every character in the game
 * @author Zach Carrillo
 */
abstract class Actor {

    /** This int stores the current health of the character*/
    public int health;
    /** This int stores the max health of the character*/
    public int maxHealth;
    /** This int stores the current number of arrows the character holds*/
    public int arrows;
    /** This String stores the current role of the character*/
    protected String role = "none";
    /** This int stores position in the ArrayList GameController.players of the character*/
    public static int position;
    /** This int stores the current number of indian chief arrows of the character*/
    public static int chief;

    /**
     * this abstract method sets the health and max health of the character
     */
    abstract void setHealth();

    /**
     * This method sets the health of a new Zombie
     * @param h the health the zombie starts with
     */
    public void setHealthZombie(int h) {
        health = h;
        maxHealth=h;
    }

    /**
     * This method adds an arrow to the characters hand
     */
    public void addArrow() {

        arrows++;
        GameController.arrows--;
    }

    /**
     * This method sets the position int to int param p
     * @param p what position in the Arraylist the character is
     */
    public void setPosition(int p) {
        position = p;
    }

    /**
     * This method adds the indian chief arrow to the characters hand
     */
    public void addChiefArrow() {
        chief++;
        GameController.chiefArrow--;
    }

    /**
     * this method sets the role String
     * @param r the role of the character
     */
    public void setRole(String r) {

        role = r;
        System.out.println("Set role to "+role);
    }

    /**
     * This method checks the role of the player and then runs the appropriate method to play their turn
     * @param p the position of the character
     */
    public void play(int p) {
        setPosition(p);
        if(role.equals("Sheriff")) {
            SheriffRound();
        }
        else if(role.equals("Outlaw")) {
            OutlawRound();
        }
        else if(role.equals("Renegade") || role.equals("Zombie Master")) {
            RenegadeRound();
        }
        else if(role.equals("Deputy")) {
            DeputyRound();
        }
        else if(role.equals("Zombie")) {
            ZombieRound();
        }
        else if(role.equals("Survivor")) {
            SurvivorRound();
        }
    }

    /**
     * this method removes 1 hp from the character
     */
    public void subtractHealth() {
        health--;
    }

    /**
     * This method adds 1 hp to the character as long as they are not at max health
     */
    public void addHealth() {

        if(health<maxHealth) health++;
    }

    /**
     * this method gets the health of the character
     * @return the health of the character as an int
     */
    public int getHealth() {
        return health;
    }
    /**
     * this method gets the max health of the character
     * @return the max health of the character as an int
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * this method gets the arrows of the character
     * @return the arrows of the character as an int
     */
    public int getArrows() {
        return arrows;
    }

    /**
     * this method adds an arrow to the characters hand
     */
    public void subtractArrows() {
        arrows--;
        GameController.arrows++;
    }


    /**
     * This method plays the characters turn if they are a zombie
     */
    protected void ZombieRound() {
        int dynamite = 0;
        int gat = 0;
        int sheriff = -1;
        for(int i = 0; i < GameController.players.size(); i++) {
            if(GameController.players.get(i).role.equals("Zombie Master")) {
                sheriff = i;
            }
        }

        System.out.println("Now Playing is Character "+position+" They are a "+ role);
        System.out.println("_______________________________________");
        Die[] die = new Die[3];
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();

        //This rolls the dice and does all the commands for them
        for(int i = 0; i < 3; i++) {
            die[i].roll();
            System.out.println("Character number " + position + " Rolled " + die[i].face);
            if (die[i].getClass().equals(Base_Die.class)) {
                if (die[i].face == 0) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if (rand == 1) {
                        while (!hasShot) {
                            counter++;
                            if (counter > 15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking " + target);
                            if (target < GameController.players.size() && target != position) {
                                if (GameController.players.get(target).getHealth() > 0) {
                                    System.out.println("They Shot " + target);
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                } else {
                                    target++;
                                }
                            } else if (target >= GameController.players.size()) {
                                target = 0;
                            } else {
                                target++;
                            }

                        }
                    } else {
                        while (!hasShot) {
                            counter++;
                            if (counter > 15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking " + target);
                            if (target >= 0 && target != position) {
                                if (GameController.players.get(target).getHealth() > 0) {
                                    System.out.println("They Shot " + target);
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                } else {
                                    target--;
                                }
                            } else if (target < 0) {
                                target = GameController.players.size() - 1;
                            } else {
                                target--;
                            }

                        }
                    }

                } else if (die[i].face == 1) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if (rand == 1) {
                        while (!hasShot) {
                            counter++;
                            if (counter > 15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking " + target);
                            if (target < GameController.players.size() && target != position) {
                                if (GameController.players.get(target).getHealth() > 0) {
                                    if (hasPassedOne) {
                                        System.out.println("They Shot " + target);
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    } else {
                                        hasPassedOne = true;
                                    }
                                } else {
                                    target++;
                                }
                            } else if (target >= GameController.players.size()) {
                                target = 0;
                            } else {
                                target++;
                            }

                        }
                    } else {
                        while (!hasShot) {
                            counter++;
                            if (counter > 15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking " + target);
                            if (target >= 0 && target != position) {
                                if (GameController.players.get(target).getHealth() > 0) {
                                    if (hasPassedOne) {
                                        System.out.println("They Shot " + target);
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    } else {
                                        hasPassedOne = true;
                                    }
                                } else {
                                    target--;
                                }
                            } else if (target < 0) {
                                target = GameController.players.size() - 1;
                            } else {
                                target--;
                            }

                        }
                    }

                } else if (die[i].face == 2) {
                    dynamite++;
                    if (dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                } else if (die[i].face == 3) {
                    if (GameController.arrows > 0) {
                        addArrow();
                    } else {
                        addChiefArrow();
                    }
                    if (GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for (int j = 0; j < GameController.players.size(); j++) {
                            while (GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if (GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while (GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if (chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if (chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                } else if (die[i].face == 4) {
                    gat++;
                    if (gat >= 3) {
                        while (GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for (int j = 0; j < GameController.players.size(); j++) {
                            if (j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                } else {
                    if (GameController.players.get(sheriff).getHealth() < GameController.players.get(sheriff).getMaxHealth()) {
                        GameController.players.get(sheriff).addHealth();
                    } else if (health < maxHealth) {
                        addHealth();
                    } else {
                        i--;
                    }
                }
            }
        }
    }

    /**
     * This method plays the characters turn if they are a Survivor
     */
    protected void SurvivorRound() {
        int dynamite = 0;
        int gat = 0;
        int zombie = -1;
        for(int i = 0; i < GameController.players.size(); i++) {
            if(GameController.players.get(i).role.equals("Zombie")||GameController.players.get(i).role.equals("Zombie Master")) {
                zombie = i;
            }
        }

        System.out.println("Now Playing is Character "+position+" They are a "+ role);
        System.out.println("_______________________________________");
        Die[] die = new Die[5];
        if(GameController.mode == 1) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Loudmouth_Die();
        }
        else if(GameController.mode == 2) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else if(GameController.mode == 3) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Loudmouth_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Base_Die();
        }

        //This rolls the dice and does all the commands for them
        for(int i = 0; i < 5; i++) {
            die[i].roll();
            System.out.println("Character number "+position+" Rolled "+die[i].face);
            if(die[i].getClass().equals(Base_Die.class)) {
                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, zombie);
                    int sheriffDistanceRight = findLeftDistance(position, zombie);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(zombie).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(zombie).subtractHealth();
                        }
                    }

                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, zombie);
                    int sheriffDistanceRight = findLeftDistance(position, zombie);
                    if(sheriffDistanceLeft == 2 || sheriffDistanceRight == 2) {
                        if(GameController.players.get(zombie).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(zombie).subtractHealth();
                        }
                    }
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Black_Die.class)) {





                if(die[i].face == 0) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    if(GameController.players.get(zombie).getHealth()>0) {
                        rand = zombie;
                    }
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 1) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    if(GameController.players.get(zombie).getHealth()>0) {
                        rand = zombie;
                    }
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    //TODO
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }





            }
            else if(die[i].getClass().equals(Coward_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, zombie);
                    int sheriffDistanceRight = findLeftDistance(position, zombie);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(zombie).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(zombie).subtractHealth();
                        }
                    }
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    if(health < maxHealth) {
                        addHealth();
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    if(arrows > 0) {
                        arrows--;
                    }
                    else {
                        Boolean usedBrokenArrow = false;
                        int chosen = 0;
                        while(!usedBrokenArrow && chosen < GameController.players.size()) {
                            if(GameController.players.get(chosen).getArrows() > 0) {
                                GameController.players.get(chosen).subtractArrows();
                                usedBrokenArrow = true;
                            }
                            chosen++;
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Loudmouth_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, zombie);
                    int sheriffDistanceRight = findLeftDistance(position, zombie);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(zombie).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(zombie).subtractHealth();
                            GameController.players.get(zombie).subtractHealth();
                        }
                    }
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, zombie);
                    int sheriffDistanceRight = findLeftDistance(position, zombie);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(zombie).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(zombie).subtractHealth();
                            GameController.players.get(zombie).subtractHealth();
                        }
                    }
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    subtractHealth();
                }

            }

        }
    }

    /**
     * This method plays the characters turn if they are a Sheriff
     */
    protected void SheriffRound() {
        int dynamite = 0;
        int gat = 0;

        System.out.println("Now Playing is Character "+position+" They are a "+ role);
        System.out.println("_______________________________________");
        Die[] die = new Die[5];
        if(GameController.mode == 1) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Coward_Die();
        }
        else if(GameController.mode == 2) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else if(GameController.mode == 3) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Coward_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Base_Die();
        }

        //This rolls the dice and does all the commands for them
        for(int i = 0; i < 5; i++) {
            die[i].roll();
            System.out.println("Character number "+position+" Rolled "+die[i].face);
            if(die[i].getClass().equals(Base_Die.class)) {
                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    System.out.println("They Shot "+target);
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    System.out.println("They Shot "+target);
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        System.out.println("They Shot "+target);
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        System.out.println("They Shot "+target);
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Black_Die.class)) {





                if(die[i].face == 0) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 1) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    //TODO
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }





            }
            else if(die[i].getClass().equals(Coward_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    if(health < maxHealth) {
                        addHealth();
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    if(arrows > 0) {
                        subtractArrows();
                    }
                    else {
                        Boolean usedBrokenArrow = false;
                        int chosen = 0;
                        while(!usedBrokenArrow && chosen < GameController.players.size()) {
                            if(GameController.players.get(chosen).getArrows() > 0) {
                                GameController.players.get(chosen).subtractArrows();
                                usedBrokenArrow = true;
                            }
                            chosen++;
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Loudmouth_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    subtractHealth();
                }

            }

        }
    }

    /**
     * This method plays the characters turn if they are an Outlaw
     */
    protected void OutlawRound() {
        int dynamite = 0;
        int gat = 0;
        int sheriff = -1;
        for(int i = 0; i < GameController.players.size(); i++) {
            if(GameController.players.get(i).role.equals("Sheriff")) {
                sheriff = i;
            }
        }

        System.out.println("Now Playing is Character "+position+" They are a "+ role);
        System.out.println("_______________________________________");
        Die[] die = new Die[5];
        if(GameController.mode == 1) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Loudmouth_Die();
        }
        else if(GameController.mode == 2) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else if(GameController.mode == 3) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Loudmouth_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Base_Die();
        }

        //This rolls the dice and does all the commands for them
        for(int i = 0; i < 5; i++) {
            die[i].roll();
            System.out.println("Character number "+position+" Rolled "+die[i].face);
            if(die[i].getClass().equals(Base_Die.class)) {
                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, sheriff);
                    int sheriffDistanceRight = findLeftDistance(position, sheriff);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(sheriff).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(sheriff).subtractHealth();
                        }
                    }

                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, sheriff);
                    int sheriffDistanceRight = findLeftDistance(position, sheriff);
                    if(sheriffDistanceLeft == 2 || sheriffDistanceRight == 2) {
                        if(GameController.players.get(sheriff).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(sheriff).subtractHealth();
                        }
                    }
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Black_Die.class)) {





                if(die[i].face == 0) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    if(GameController.players.get(sheriff).getHealth()>0) {
                        rand = sheriff;
                    }
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 1) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    if(GameController.players.get(sheriff).getHealth()>0) {
                        rand = sheriff;
                    }
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    //TODO
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }





            }
            else if(die[i].getClass().equals(Coward_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, sheriff);
                    int sheriffDistanceRight = findLeftDistance(position, sheriff);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(sheriff).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(sheriff).subtractHealth();
                        }
                    }
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    if(health < maxHealth) {
                        addHealth();
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    if(arrows > 0) {
                        arrows--;
                    }
                    else {
                        Boolean usedBrokenArrow = false;
                        int chosen = 0;
                        while(!usedBrokenArrow && chosen < GameController.players.size()) {
                            if(GameController.players.get(chosen).getArrows() > 0) {
                                GameController.players.get(chosen).subtractArrows();
                                usedBrokenArrow = true;
                            }
                            chosen++;
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Loudmouth_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, sheriff);
                    int sheriffDistanceRight = findLeftDistance(position, sheriff);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(sheriff).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(sheriff).subtractHealth();
                            GameController.players.get(sheriff).subtractHealth();
                        }
                    }
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int sheriffDistanceLeft = findRightDistance(position, sheriff);
                    int sheriffDistanceRight = findLeftDistance(position, sheriff);
                    if(sheriffDistanceLeft == 1 || sheriffDistanceRight == 1) {
                        if(GameController.players.get(sheriff).getHealth() > 0) {
                            hasShot = true;
                            GameController.players.get(sheriff).subtractHealth();
                            GameController.players.get(sheriff).subtractHealth();
                        }
                    }
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    subtractHealth();
                }

            }

        }
    }

    /**
     * This method plays the characters turn if they are a Renegade
     */
    protected void RenegadeRound() {
        int dynamite = 0;
        int gat = 0;

        System.out.println("Now Playing is Character "+position+" They are a "+ role);
        System.out.println("_______________________________________");
        Die[] die = new Die[5];
        if(GameController.mode == 1) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Loudmouth_Die();
        }
        else if(GameController.mode == 2) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else if(GameController.mode == 3) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Loudmouth_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Base_Die();
        }

        //This rolls the dice and does all the commands for them
        for(int i = 0; i < 5; i++) {
            die[i].roll();
            System.out.println("Character number "+position+" Rolled "+die[i].face);
            if(die[i].getClass().equals(Base_Die.class)) {
                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Black_Die.class)) {





                if(die[i].face == 0) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 1) {
                    //TODO tokens
                    int rand = (int) (Math.random()*GameController.players.size());
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    //TODO
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }





            }
            else if(die[i].getClass().equals(Coward_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    if(health < maxHealth) {
                        addHealth();
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    if(arrows > 0) {
                        arrows--;
                    }
                    else {
                        Boolean usedBrokenArrow = false;
                        int chosen = 0;
                        while(!usedBrokenArrow && chosen < GameController.players.size()) {
                            if(GameController.players.get(chosen).getArrows() > 0) {
                                GameController.players.get(chosen).subtractArrows();
                                usedBrokenArrow = true;
                            }
                            chosen++;
                        }
                    }
                }
                else {
                    if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Loudmouth_Die.class)) {

                if(die[i].face == 0) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    subtractHealth();
                }

            }

        }
    }

    /**
     * This method plays the characters turn if they are a Deputy
     */
    protected void DeputyRound() {
        int dynamite = 0;
        int gat = 0;
        int sheriff = -1;
        for(int i = 0; i < GameController.players.size(); i++) {
            if(GameController.players.get(i).role.equals("Sheriff")) {
                sheriff = i;
            }
        }

        System.out.println("Now Playing is Character "+position+" They are a "+ role);
        System.out.println("_______________________________________");
        Die[] die = new Die[5];
        if(GameController.mode == 1) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Coward_Die();
        }
        else if(GameController.mode == 2) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else if(GameController.mode == 3) {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Coward_Die();
            die[3] = new Black_Die();
            die[4] = new Black_Die();
        }
        else {
            die[0] = new Base_Die();
            die[1] = new Base_Die();
            die[2] = new Base_Die();
            die[3] = new Base_Die();
            die[4] = new Base_Die();
        }

        //This rolls the dice and does all the commands for them
        for(int i = 0; i < 5; i++) {
            die[i].roll();
            System.out.println("Character number "+position+" Rolled "+die[i].face);
            if(die[i].getClass().equals(Base_Die.class)) {
                if(die[i].face == 0) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    System.out.println("They Shot "+target);
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    System.out.println("They Shot "+target);
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking "+target);
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        System.out.println("They Shot "+target);
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            System.out.println("Checking "+target);
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        System.out.println("They Shot "+target);
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    if(GameController.players.get(sheriff).getHealth() < GameController.players.get(sheriff).getMaxHealth()) {
                        GameController.players.get(sheriff).addHealth();
                    }
                    else if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Black_Die.class)) {





                if(die[i].face == 0) {
                    //TODO tokens
                    int counter = 0;
                    int rand = (int) (Math.random()*GameController.players.size());
                    while (rand == sheriff) {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                        while (rand == sheriff && counter<15) {
                            rand = (int) (Math.random()*GameController.players.size());
                            counter++;
                        }
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 1) {
                    //TODO tokens
                    int counter = 0;
                    int rand = (int) (Math.random()*GameController.players.size());
                    while (rand == sheriff) {
                        rand = (int) (Math.random()*GameController.players.size());
                    }
                    while(GameController.players.get(rand).getHealth() <= 0)  {
                        rand = (int) (Math.random()*GameController.players.size());
                        while (rand == sheriff && counter<15) {
                            counter++;
                            rand = (int) (Math.random()*GameController.players.size());
                        }
                    }
                    System.out.println("Past");
                    Die duel = new Black_Die();
                    duel.face = 1;
                    Boolean dealDamageToSelf = false;
                    while(duel.face == 1 || duel.face==2) {
                        duel.canReroll = true;
                        duel.roll();
                        if(duel.face == 1 || duel.face==2) {
                            dealDamageToSelf = !dealDamageToSelf;
                        }
                    }
                    if(dealDamageToSelf) {
                        subtractHealth();
                    }
                    else {
                        GameController.players.get(rand).subtractHealth();
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    if(GameController.players.get(sheriff).getHealth() < GameController.players.get(sheriff).getMaxHealth()) {
                        GameController.players.get(sheriff).addHealth();
                    }
                    else if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }





            }
            else if(die[i].getClass().equals(Coward_Die.class)) {

                if(die[i].face == 0) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            if(target < GameController.players.size() && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(target != sheriff) {
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        while (!hasShot) {
                                            if(target >= 0 && target != position && target != sheriff) {
                                                if(GameController.players.get(target).getHealth() > 0) {
                                                    GameController.players.get(target).subtractHealth();
                                                    hasShot = true;
                                                }
                                                else {
                                                    target--;
                                                    counter++;
                                                    if(counter>15) {
                                                        hasShot = true;
                                                        GameController.players.get(sheriff).subtractHealth();
                                                    }
                                                }
                                            }
                                            else if(target < 0) {
                                                target = GameController.players.size()-1;
                                            }
                                            else {
                                                target--;
                                                counter++;
                                                if(counter>15) {
                                                    hasShot = true;
                                                    GameController.players.get(sheriff).subtractHealth();
                                                }
                                            }

                                        }
                                    }

                                }
                                else {
                                    target++;
                                    counter++;
                                    if(counter>15) {
                                        hasShot = true;
                                        GameController.players.get(sheriff).subtractHealth();
                                    }
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                                counter++;
                                if(counter>15) {
                                    hasShot = true;
                                    GameController.players.get(sheriff).subtractHealth();
                                }
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            if(target >= 0 && target != position && target != sheriff) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    if(GameController.players.get(sheriff).getHealth() < GameController.players.get(sheriff).getMaxHealth()) {
                        GameController.players.get(sheriff).addHealth();
                        GameController.players.get(sheriff).addHealth();
                    }
                    else if(health < maxHealth) {
                        addHealth();
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    if(GameController.players.get(sheriff).getArrows() > 0) {
                        GameController.players.get(sheriff).subtractArrows();
                    }
                    else if(arrows > 0) {
                        arrows--;
                    }
                    else {
                        Boolean usedBrokenArrow = false;
                        int chosen = 0;
                        while(!usedBrokenArrow && chosen < GameController.players.size()) {
                            if(GameController.players.get(chosen).getArrows() > 0) {
                                GameController.players.get(chosen).subtractArrows();
                                usedBrokenArrow = true;
                            }
                            chosen++;
                        }
                    }
                }
                else {
                    if(GameController.players.get(sheriff).getHealth() < GameController.players.get(sheriff).getMaxHealth()) {
                        addHealth();
                    }
                    else if(health < maxHealth) {
                        addHealth();
                    }
                    else {
                        i--;
                    }
                }
            }
            else if(die[i].getClass().equals(Loudmouth_Die.class)) {

                if(die[i].face == 0) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            if(target < GameController.players.size() && target != position && target != sheriff) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            if(target >= 0 && target != position && target != sheriff) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    GameController.players.get(target).subtractHealth();
                                    GameController.players.get(target).subtractHealth();
                                    hasShot = true;
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 1) {
                    int counter = 0;
                    int rand = (int) (Math.random());
                    Boolean hasShot = false;
                    Boolean hasPassedOne = false;
                    int target = position;
                    if(rand == 1) {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            if(target < GameController.players.size() && target != position && target != sheriff) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target++;
                                }
                            }
                            else if(target >= GameController.players.size()) {
                                target = 0;
                            }
                            else {
                                target++;
                            }

                        }
                    }
                    else {
                        while (!hasShot) {
                            counter++;
                            if(counter>15) {
                                hasShot = true;
                                GameController.players.get(sheriff).subtractHealth();
                            }
                            if(target >= 0 && target != position) {
                                if(GameController.players.get(target).getHealth() > 0) {
                                    if(hasPassedOne && target != sheriff) {
                                        GameController.players.get(target).subtractHealth();
                                        GameController.players.get(target).subtractHealth();
                                        hasShot = true;
                                    }
                                    else {
                                        hasPassedOne = true;
                                    }
                                }
                                else {
                                    target--;
                                }
                            }
                            else if(target < 0) {
                                target = GameController.players.size()-1;
                            }
                            else {
                                target--;
                            }

                        }
                    }

                }
                else if(die[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        subtractHealth();
                        dynamite = 0;
                    }
                }
                else if(die[i].face == 3) {
                    if(GameController.arrows > 0) {
                        addArrow();
                    }
                    else {
                        addChiefArrow();
                    }
                    if(GameController.arrows == 0 && GameController.chiefArrow == 0) {
                        for(int j = 0; j < GameController.players.size(); j++) {
                            while(GameController.players.get(j).getArrows() > 0) {
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractArrows();
                                if(GameController.players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(GameController.players.get(j).getArrows() > 0) {
                                        GameController.players.get(j).subtractArrows();
                                        if(chief == 1) {
                                            chief--;
                                            GameController.chiefArrow = 1;
                                        }
                                    }
                                }
                            }
                            if(chief == 1) {
                                chief--;
                                GameController.chiefArrow = 1;
                                GameController.players.get(j).subtractHealth();
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else if(die[i].face == 4) {
                    gat++;
                    if(gat>=3) {
                        while(GameController.players.get(position).getArrows() > 0) {
                            GameController.players.get(position).subtractArrows();
                        }
                        for(int j = 0; j < GameController.players.size(); j++) {
                            if(j != position && GameController.players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }
                        }
                    }
                }
                else {
                    subtractHealth();
                }
            }
        }
    }

    /**
     * This method finds the distance of a player from the left of the current player (used to calculate who they can shoot)
     */
    private int findLeftDistance(int p, int s) {
        int distance;
        if(p>s) {
            distance = p-s;
        }
        else {
            distance = GameController.players.size()-s+p;
        }
        return distance;
    }

    /**
     * This method finds the distance of a player from the right of the current player (used to calculate who they can shoot)
     */
    private int findRightDistance(int p, int s) {
        int distance;
        if(s>p) {
            distance = s-p;
        }
        else {
            distance = GameController.players.size()-p+s;
        }
        return distance;
    }
}
