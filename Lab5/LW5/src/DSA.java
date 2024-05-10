import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class DSA {
    private final BigInteger p, q, g, x, y;
    private final int keySize; // in bits

    public DSA(int keySize) {
        this.keySize = Math.max(keySize, 16);
        BigInteger[] keys = generateKeys();
        this.p = keys[0];
        this.q = keys[1];
        this.g = keys[2];
        this.x = keys[3];
        this.y = this.g.modPow(this.x, this.p);
    }

    private BigInteger[] generateKeys() {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(this.keySize, random);

        List<BigInteger> factors = primeFactors(p.subtract(BigInteger.ONE));
        BigInteger q = factors.get(random.nextInt(factors.size()));

        BigInteger h = new BigInteger(p.bitLength() - 1, random).add(BigInteger.ONE);
        BigInteger exp = p.subtract(BigInteger.ONE).divide(q); // Calculate the exponent (p-1)/q
        BigInteger g = h.modPow(exp, p); // Calculate g = h^((p-1)/q) mod p

        BigInteger x = new BigInteger(q.bitLength(), random);
        return new BigInteger[]{p, q, g, x};
    }

    public BigInteger[] signMessage(String messagePath) throws IOException {
        byte[] hash = ByteBuffer.allocate(4).putInt(JOAATHash.calculateHash(messagePath)).array();
        BigInteger h = new BigInteger(1, hash);

        BigInteger k;
        BigInteger r, s;
        SecureRandom random = new SecureRandom();
        do {
            k = new BigInteger(q.bitLength(), random);
            r = g.modPow(k, p).mod(q);
            s = k.modInverse(q).multiply(h.add(x.multiply(r))).mod(q);
        } while (r.equals(BigInteger.ZERO) || s.equals(BigInteger.ZERO));

        return new BigInteger[] { r, s };
    }

    public boolean verifySignature(String messagePath, BigInteger r, BigInteger s) throws IOException {
        byte[] hash = ByteBuffer.allocate(4).putInt(JOAATHash.calculateHash(messagePath)).array();
        BigInteger h = new BigInteger(1, hash);

        BigInteger w = s.modInverse(this.q);
        BigInteger u1 = h.multiply(w).mod(this.q);
        BigInteger u2 = r.multiply(w).mod(this.q);
        BigInteger v = this.g.modPow(u1, this.p).multiply(this.y.modPow(u2, this.p)).mod(this.p).mod(this.q);

        return v.equals(r);
    }

    private static List<BigInteger> primeFactors(BigInteger n) {
        List<BigInteger> factors = new ArrayList<>();
        BigInteger i = BigInteger.TWO;
        while (i.multiply(i).compareTo(n) <= 0) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
            }
            i = i.add(BigInteger.ONE);
        }
        if (n.compareTo(BigInteger.ONE) > 0) {
            factors.add(n);
        }
        return factors;
    }}
