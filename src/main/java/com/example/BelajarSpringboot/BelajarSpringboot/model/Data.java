package com.example.BelajarSpringboot.BelajarSpringboot.model;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_makanan")
    private String dataMakanan;

    @Column(name = "data_minuman")
    private String dataMinuman;

    @Column(name = "data_pakaian")
    private String dataPakaian;

    @Column(name = "data_keluarga")
    private String dataKeluarga;

    @Column(name = "data_sekolah")
    private String dataSekolah;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Data(Long id, User user, String dataMakanan, String dataMinuman, String dataPakaian, String dataKeluarga, String dataSekolah) {
        this.id = id;
        this.user = user;
        this.dataMakanan = dataMakanan;
        this.dataMinuman = dataMinuman;
        this.dataPakaian = dataPakaian;
        this.dataKeluarga = dataKeluarga;
        this.dataSekolah = dataSekolah;
    }

    public Data() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataMakanan() {
        return dataMakanan;
    }

    public void setDataMakanan(String dataMakanan) {
        this.dataMakanan = dataMakanan;
    }

    public String getDataMinuman() {
        return dataMinuman;
    }

    public void setDataMinuman(String dataMinuman) {
        this.dataMinuman = dataMinuman;
    }

    public String getDataPakaian() {
        return dataPakaian;
    }

    public void setDataPakaian(String dataPakaian) {
        this.dataPakaian = dataPakaian;
    }

    public String getDataKeluarga() {
        return dataKeluarga;
    }

    public void setDataKeluarga(String dataKeluarga) {
        this.dataKeluarga = dataKeluarga;
    }

    public String getDataSekolah() {
        return dataSekolah;
    }

    public void setDataSekolah(String dataSekolah) {
        this.dataSekolah = dataSekolah;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
