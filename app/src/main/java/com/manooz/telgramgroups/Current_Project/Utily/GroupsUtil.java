package com.manooz.telgramgroups.Current_Project.Utily;


import android.content.Context;

import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.R;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GroupsUtil  {
    private static final String TAG = "GroupUtil";

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private static final String RESTAURANT_URL_FMT = "https://storage.googleapis.com/firestorequickstarts.appspot.com/food_%d.png";

    private static final int MAX_IMAGE_NUM = 22;

    public static final String[] NAME_FIRST_WORDS = {
            "Foo",
            "Bar",
            "Baz",
            "Qux",
            "Fire",
            "Sam's",
            "World Famous",
            "Google",
            "The Best",
    };

    public static final String[] NAME_SECOND_WORDS = {
            "Group",
            "Cafe",
            "Spot",
            "Eatin' Place",
            "Eatery",
            "Drive Thru",
            "Diner",
    };



    public static int[] prices = new int[]{10, 20, 30,40,50,60,70,80,90,100,200,220,240,260,280,300,411,425,521,123,555,256,742,135,632,235,162};

    public static Group_Object getRandom(Context context) {
        Group_Object group = new Group_Object();
        Random random = new Random();

        // Cities (first elemnt is 'Any')
        String[] cities = context.getResources().getStringArray(R.array.countries);
        cities = Arrays.copyOfRange(cities, 1, cities.length);

        // Categories (first element is 'Any')
        String[] categories = context.getResources().getStringArray(R.array.mListOfCatagories);
        categories = Arrays.copyOfRange(categories, 1, categories.length);


        group.setGroupName(getRandomName(random));
        group.setUserName(getRandomString(cities, random));
        group.setCategories(getRandomString(categories, random));
        group.setPhoto(getRandomImageUrl(random));
        group.setmNumOfLiks( getRandomInt(prices, random));
        group.setRatings(getRandomRating(random));
        group.setmNumOfRatings(random.nextInt(20));
        group.setGroupDesc(getRandomString(cities,random));
        group.setGroupLink(getRandomString(cities,random));

        return group;
    }


    /**
     * Get a randomLongString image.
     */
    private static String getRandomImageUrl(Random random) {
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        int id = random.nextInt(MAX_IMAGE_NUM) + 1;

        return String.format(Locale.getDefault(), RESTAURANT_URL_FMT, id);
    }

    /**
     * Get price represented as dollar signs.
     */
    public static String getPriceString(Group_Object group) {
        return getPriceString(group.getmNumOfLiks());
    }

    /**
     * Get price represented as dollar signs.
     */
    public static String getPriceString(int priceInt) {
        switch (priceInt) {
            case 1:
                return "$";
            case 2:
                return "$$";
            case 3:
            default:
                return "$$$";
        }
    }

    public static double getRandomRating(Random random) {
        double min = 1.0;
        return min + (random.nextDouble() * 4.0);
    }

    public static String getRandomName(Random random) {
        return getRandomString(NAME_FIRST_WORDS, random) + " "
                + getRandomString(NAME_SECOND_WORDS, random);
    }

    public static String getRandomString(String[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

    public static int getRandomInt(int[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }
    public static int setRandomInt( Random random) {
        int ind = random.nextInt(prices.length);
        return prices[ind];
    }

}
