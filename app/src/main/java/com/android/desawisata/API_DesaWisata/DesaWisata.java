package com.android.desawisata.API_DesaWisata;

public class DesaWisata {

    int id, kegiatan_id;
    int desa_id, tempat_wisata_id, foto_id, video_id;
    String nama_desa, status, nama_wisata, alamat_wisata, sejarah_wisata, demografi, potensi;
    double lat, lng;
    String nama_kegiatan, deskripsi, foto;
    String file_foto;
    String file_video;
    String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKegiatan_id() {
        return kegiatan_id;
    }

    public void setKegiatan_id(int kegiatan_id) {
        this.kegiatan_id = kegiatan_id;
    }

    public int getDesa_id() {
        return desa_id;
    }

    public void setDesa_id(int desa_id) {
        this.desa_id = desa_id;
    }

    public int getTempat_wisata_id() {
        return tempat_wisata_id;
    }

    public void setTempat_wisata_id(int tempat_wisata_id) {
        this.tempat_wisata_id = tempat_wisata_id;
    }

    public int getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(int foto_id) {
        this.foto_id = foto_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getNama_desa() {
        return nama_desa;
    }

    public void setNama_desa(String nama_desa) {
        this.nama_desa = nama_desa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getAlamat_wisata() {
        return alamat_wisata;
    }

    public void setAlamat_wisata(String alamat_wisata) {
        this.alamat_wisata = alamat_wisata;
    }

    public String getSejarah_wisata() {
        return sejarah_wisata;
    }

    public void setSejarah_wisata(String sejarah_wisata) {
        this.sejarah_wisata = sejarah_wisata;
    }

    public String getDemografi() {
        return demografi;
    }

    public void setDemografi(String demografi) {
        this.demografi = demografi;
    }

    public String getPotensi() {
        return potensi;
    }

    public void setPotensi(String potensi) {
        this.potensi = potensi;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFile_foto() {
        return file_foto;
    }

    public void setFile_foto(String file_foto) {
        this.file_foto = file_foto;
    }

    public String getFile_video() {
        return file_video;
    }

    public void setFile_video(String file_video) {
        this.file_video = file_video;
    }
}
