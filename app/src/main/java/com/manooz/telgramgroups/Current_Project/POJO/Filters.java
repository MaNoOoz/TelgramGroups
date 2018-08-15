/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.manooz.telgramgroups.Current_Project.POJO;

import android.content.Context;
import android.text.TextUtils;

import com.google.firebase.firestore.Query;
import com.manooz.telgramgroups.Current_Project.Utily.GroupsUtil;
import com.manooz.telgramgroups.R;

/**
 * Object for passing filters around.
 */
public class Filters {

    private String category = null;
    private String city = null;
    private int likes = -1;
    
    private String sortBy = null;
    private Query.Direction sortDirection = null;


    public Filters() {}

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public static Filters getDefault() {
        Filters filters = new Filters();
        filters.setSortBy("timestamp");
        filters.setSortDirection(Query.Direction.DESCENDING);

        return filters;
    }
    public boolean hasCategory() {
        return !(TextUtils.isEmpty(category));
    }

    public boolean hasSortBy() {
        return !(TextUtils.isEmpty(sortBy));
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Query.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Query.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSearchDescription(Context context) {
        StringBuilder desc = new StringBuilder();

        if (category == null && city == null) {
            desc.append("<b>");
            desc.append(context.getString(R.string.all_restaurants));
            desc.append("</b>");
        }

        if (category != null) {
            desc.append("<b>");
            desc.append(category);
            desc.append("</b>");
        }

        if (category != null && city != null) {
            desc.append(" in ");
        }

        if (city != null) {
            desc.append("<b>");
            desc.append(city);
            desc.append("</b>");
        }

        if (likes > 0) {
            desc.append(" for ");
            desc.append("<b>");
            desc.append(GroupsUtil.getPriceString(likes));
            desc.append("</b>");
        }

        return desc.toString();
    }

    public String getOrderDescription(Context context) {
        if (Group_Object.FIELD_Ratings.equals(sortBy)) {
            return context.getString(R.string.sorted_by_rating);
        } else if (Group_Object.FIELD_NumOfLiks.equals(sortBy)) {
            return context.getString(R.string.sorted_by_popularity);
        } else {
            return context.getString(R.string.sorted_by_comments);
        }
    }
}
