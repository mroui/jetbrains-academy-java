class ComplexNumber {

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public int hashCode() {
        return (int) (10 * re + im);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComplexNumber)) {
            return false;
        }
        ComplexNumber complexNumber = (ComplexNumber) obj;
        return re == complexNumber.re && im == complexNumber.im;
    }
}