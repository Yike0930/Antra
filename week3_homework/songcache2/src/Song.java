public class Song implements Comparable<Song>{
    String songId;
    int playTimes;

    public Song(String songId, int playTimes) {
        this.songId = songId;
        this.playTimes = playTimes;
    }

    @Override
    public int compareTo(Song o) {
        return o.playTimes - this.playTimes;
    }
}
