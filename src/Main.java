
import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static int[] heroesHealth = {300, 250, 200, 220, 350, 180, 190, 150};
    public static int[] heroesDamage = {20, 25, 21, 0, 5, 10, 8, 12};
    public static String[] heroesTypeAttack = {"Logan", "Ciclops", "Wizard", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static String bossBarrier;
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistic();
        while (!isGameFinish()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        bossTypeBarrier();
        bossHits();
        golemTank();
        heroesHits();
        healing();
        evasionChance();
        fury();
        stun();
        printStatistic();
    }

    public static void bossTypeBarrier() {
        Random random = new Random();
        int index = random.nextInt(heroesTypeAttack.length);
        bossBarrier = heroesTypeAttack[index];
        System.out.println("Boss barrier equals " + bossBarrier);
    }


    public static boolean stun() {
        Random stanRand = new Random();
        boolean chanceStan = stanRand.nextBoolean();
        int index = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesTypeAttack[i] == "Thor") {
                index = i;
            }
            if (heroesHealth.length > 0 && chanceStan == true) {
                if (heroesHealth[index] > 0) {
                    bossDamage = 0;
                    System.out.println("Worked Thors stan!!!");
                    break;
                }
            }
        }
        return chanceStan;
    }

    public static void fury() {
        int index = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesTypeAttack[i] == "Berserk") ;
            index = i;
            if (heroesHealth[i] == heroesHealth[i] - bossDamage) {
                bossDamage = 50 - 5;
            }
        }
    }

    public static boolean evasionChance() {
        Random r = new Random();
        boolean chanceAttack = r.nextBoolean();
        int index = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesTypeAttack[index] == "Lucky") {
                index = i;
            }
            if (heroesHealth[index] >= 0 && chanceAttack == true) {
                heroesHealth[index] = heroesHealth[index] - bossDamage * 0;
                System.out.println("Worked Luckys evasion chance!!!");
                break;
            }
        }
        return chanceAttack;
    }

    public static void golemTank() {
        int index = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesTypeAttack[index] == "Golem") {
                index = i;
            }
            if (heroesHealth[i] >0) {
                heroesHealth[i] = heroesHealth[i] + (bossDamage / 5);
            }
        }
    }

    public static void healing() {
        Random randomHeal = new Random();
        boolean chanceHeal = randomHeal.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] == heroesHealth[3] && heroesHealth[i] > 0) {
                continue;
            }
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && chanceHeal == true) {
                heroesHealth[i] = heroesHealth[i] + 10;
                System.out.println("Medic heal " + heroesTypeAttack[i]);
                break;
            }
        }
    }

    public static boolean isGameFinish() {
        if (bossHealth <= 0) {
            System.out.println("HEROES WON!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int j : heroesHealth) {
            if (j > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("BOSS VON!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesDamage[i]; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void printStatistic() {
        System.out.println("       *" + "  Round " + roundNumber + "  *  ");
        System.out.println("Boss health - " + bossHealth + ", " + " damage [" + bossDamage + "]");

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println("Hero " + heroesTypeAttack[i] + "  " + " health - " + heroesHealth[i] + ", " + "damage [" + heroesDamage[i] + "]");
        }
        System.out.println("_  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _");
    }
}
