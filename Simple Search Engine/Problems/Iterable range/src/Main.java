import java.util.Iterator;

class Range implements Iterable<Long> {

    private long fromInclusive;
    private long toExclusive;

    public Range(long from, long to) {
        this.fromInclusive = from;
        this.toExclusive = to;
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return fromInclusive < toExclusive;
            }

            @Override
            public Long next() {
                fromInclusive++;
                return fromInclusive - 1;
            }
        };
    }
}