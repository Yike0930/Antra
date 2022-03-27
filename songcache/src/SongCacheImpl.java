import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SongCacheImpl implements SongCache{
    Map<String, DoubleLinkedNode> map = new ConcurrentHashMap<>();
    DoubleLinkedList songList = new DoubleLinkedList();

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        if(!map.containsKey(songId)) {
            DoubleLinkedNode songNode = new DoubleLinkedNode(songId, numPlays);
            map.put(songId, songNode);
            songList.put(songNode);
        }else {
            DoubleLinkedNode songNode = map.get(songId);
            songNode.value = songNode.value + numPlays;
            map.put(songId, songNode);
            songList.delete(songNode);
            songList.put(songNode);
        }
    }

    @Override
    public int getPlaysForSong(String songId) {
        if(!map.containsKey(songId)) return -1;
        return map.get(songId).value;
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        return songList.getTopN(n);
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


