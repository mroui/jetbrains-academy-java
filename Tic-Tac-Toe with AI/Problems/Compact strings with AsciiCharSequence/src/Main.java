import java.util.Arrays;

class AsciiCharSequence implements CharSequence {

    private byte[] sequence;

    public AsciiCharSequence(byte[] sequence) {
        this.sequence = sequence.clone();
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int i) {
        return (char) sequence[i];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(sequence, start, end));
    }

    @Override
    public String toString() {
        return new String(sequence);
    }
}