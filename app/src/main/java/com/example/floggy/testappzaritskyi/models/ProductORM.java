package com.example.floggy.testappzaritskyi.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProductORM {
    private static final String TAG = "ProductORM";

    private static final String TABLE_NAME_CATEGORY = "category";

    private static final String COLUMN_CATEGORY_NAME_TYPE = "TEXT";
    private static final String COLUMN_CATEGORY_NAME = "name";

    private static final String COLUMN_CATEGORY_KEYWORDS_TYPE = "TEXT";
    private static final String COLUMN_CATEGORY_KEYWORDS = "keywords";

    private static final String COMMA_SEP = ", ";

    private static final String TABLE_NAME_PRODUCT = "product";

    private static final String COLUMN_PRODUCT_TITLE_TYPE = "TEXT";
    private static final String COLUMN_PRODUCT_TITLE = "title";

    private static final String COLUMN_PRODUCT_DESCRIPTION_TYPE = "TEXT";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "description";

    private static final String COLUMN_PRODUCT_PRICE_TYPE = "TEXT";
    private static final String COLUMN_PRODUCT_PRICE = "price";

    private static final String COLUMN_PRODUCT_IMAGE_URL_TYPE = "TEXT";
    private static final String COLUMN_PRODUCT_IMAGE_URL = "img";




    public static final String SQL_CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + TABLE_NAME_CATEGORY + " (" +
                    COLUMN_CATEGORY_NAME + " " + COLUMN_CATEGORY_NAME_TYPE + COMMA_SEP +
                    COLUMN_CATEGORY_KEYWORDS  + " " + COLUMN_CATEGORY_KEYWORDS_TYPE +
                    ")";

    public static final String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_NAME_PRODUCT + " (" +
            COLUMN_PRODUCT_TITLE + " " + COLUMN_PRODUCT_TITLE_TYPE + COMMA_SEP +
            COLUMN_PRODUCT_DESCRIPTION  + " " + COLUMN_PRODUCT_DESCRIPTION_TYPE + COMMA_SEP +
            COLUMN_PRODUCT_PRICE  + " " + COLUMN_PRODUCT_PRICE_TYPE + COMMA_SEP +
            COLUMN_PRODUCT_IMAGE_URL  + " " + COLUMN_PRODUCT_IMAGE_URL_TYPE +
            ")";

    public static final String SQL_DROP_TABLE_CATEGORY =
            "DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORY;

    public static final String SQL_DROP_TABLE_PRODUCT =
            "DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCT;

    public static void insertProducts(Context context, ProductData product) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        ContentValues values = productToContentValues(product);
        long productID = database.insert(ProductORM.TABLE_NAME_PRODUCT, "null", values);
        Log.i(TAG, "Inserted new Product with ID: " + productID);
        //database.close();
    }

    private static ContentValues productToContentValues(ProductData product) {
        ContentValues values = new ContentValues();
        values.put(ProductORM.COLUMN_PRODUCT_TITLE, product.getTitle());
        values.put(ProductORM.COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(ProductORM.COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(ProductORM.COLUMN_PRODUCT_IMAGE_URL, product.getImgURLData());

        return values;
    }

    public static List<ProductData> getProducts(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + ProductORM.TABLE_NAME_PRODUCT, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Products...");
        List<ProductData> ProductList = new ArrayList<>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ProductData product = cursorToProduct(cursor);
                ProductList.add(product);
                cursor.moveToNext();
            }
            Log.i(TAG, "Products loaded successfully.");
        }

        database.close();

        return ProductList;
    }

    private static ProductData cursorToProduct(Cursor cursor) {
        ProductData product = new ProductData();
        product.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_TITLE)));
        product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
        product.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
        product.setImgURLData(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE_URL)));

        return product;
    }



    public static void insertCategories(Context context, List<CategoryData> categories) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        for (CategoryData i:
             categories) {
            Log.i(TAG, "insertCategories: i = " + i.toString());
            ContentValues values = categoryToContentValues(i);
            long categoryID = database.insert(ProductORM.TABLE_NAME_CATEGORY, "null", values);
            Log.i(TAG, "Inserted new Category with ID: " + categoryID);
        }
        database.close();
    }


    private static ContentValues categoryToContentValues(CategoryData category) {
        ContentValues values = new ContentValues();
        values.put(ProductORM.COLUMN_CATEGORY_NAME, category.getName());
        values.put(ProductORM.COLUMN_CATEGORY_KEYWORDS, category.getKeywords());

        return values;
    }

    public static List<CategoryData> getCategories(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + ProductORM.TABLE_NAME_CATEGORY, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Posts...");
        List<CategoryData> categoriesList = new ArrayList<>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CategoryData category = cursorToCategory(cursor);
                categoriesList.add(category);
                cursor.moveToNext();
            }
            Log.i(TAG, "Categories loaded successfully.");
        }

        database.close();

        return categoriesList;
    }

    private static CategoryData cursorToCategory(Cursor cursor) {
        CategoryData category = new CategoryData();
        category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
        category.setKeywords(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_KEYWORDS)));

        return category;
    }
}
