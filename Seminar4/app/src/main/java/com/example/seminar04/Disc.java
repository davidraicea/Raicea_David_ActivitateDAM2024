package com.example.seminar04;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Discuri")
public class Disc implements Parcelable {
    @PrimaryKey
    @NotNull
    private String nume;
    private float raza;
    private float diametru;

    private String vechime;

    private boolean important;

    private double arie;

    public Disc(String nume, float raza,boolean important,String vechime) {
        this.nume = nume;
        this.raza = raza;
        this.vechime = vechime;
        this.diametru = 2*raza;
        this.arie = Math.PI * raza * raza;
        this.important = important;
    }

    protected Disc(Parcel in) {
        nume = in.readString();
        raza = in.readFloat();
        diametru = in.readFloat();
        vechime = in.readString();
        important = in.readByte() != 0;
        arie = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeFloat(raza);
        dest.writeFloat(diametru);
        dest.writeString(vechime);
        dest.writeByte((byte) (important ? 1 : 0));
        dest.writeDouble(arie);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Disc> CREATOR = new Creator<Disc>() {
        @Override
        public Disc createFromParcel(Parcel in) {
            return new Disc(in);
        }

        @Override
        public Disc[] newArray(int size) {
            return new Disc[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getRaza() {
        return raza;
    }

    public void setRaza(float raza) {
        this.raza = raza;
    }

    public float getDiametru() {
        return diametru;
    }

    public void setDiametru(float diametru) {
        this.diametru = diametru;
    }

    public double getArie() {
        return arie;
    }

    public void setArie(double arie) {
        this.arie = arie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Disc{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", raza=").append(raza);
        sb.append(", diametru=").append(diametru);
        sb.append(", vechime='").append(vechime).append('\'');
        sb.append(", important=").append(important);
        sb.append(", arie=").append(arie);
        sb.append('}');
        return sb.toString();
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getVechime() {
        return vechime;
    }

    public void setVechime(String vechime) {
        this.vechime = vechime;
    }


}
