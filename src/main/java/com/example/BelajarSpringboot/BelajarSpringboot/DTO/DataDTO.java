package com.example.BelajarSpringboot.BelajarSpringboot.DTO;

public class DataDTO {
    private Long id;
    private Long idUser;
    private String dataMakanan;
    private String dataMinuman;
    private String dataPakaian;
    private String dataKeluarga;
    private String dataSekolah;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
}
