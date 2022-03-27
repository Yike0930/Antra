import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class SongCacheImpl implements SongCache{
    Map<String, Song> map = new ConcurrentHashMap<>();
    BlockingQueue<Song> pq = new PriorityBlockingQueue<>();

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        if(!map.containsKey(songId)) {
            Song song = new Song(songId, numPlays);
            map.put(songId, song);
            pq.offer(song);
        }else {
            Song song = map.get(songId);
            pq.remove(song);
            song.playTimes += numPlays;
            map.put(songId, song);
            pq.offer(song);
        }
    }

    @Override
    public int getPlaysForSong(String songId) {
        if(!map.containsKey(songId)) return -1;
        return map.get(songId).playTimes;
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        List<String> res = new ArrayList<>();
        Iterator<Song> itr = pq.iterator();
        while(itr.hasNext() && n > 0){
            res.add(itr.next().songId);
            n--;
        }
        return res;
    }

    public static void main(String[] args) {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);
        System.out.println(cache.getTopNSongsPlayed(2));
    }
}
