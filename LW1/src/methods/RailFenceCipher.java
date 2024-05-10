package methods;

public class RailFenceCipher {
    int numRails;

    public RailFenceCipher(int rails) {
        this.numRails = rails;
    }

    String getDecryptedData(String data) {
        return getTranslatedData(data, false);
    }

    String getEncryptedData(String data) {
        return getTranslatedData(data, true);
    }

    String getTranslatedData(String data, boolean encrypt) {
        char[] result = new char[data.length()];
        int n = 0;
        for (int k = 0; k < numRails; k++) {
            int index = k;
            boolean down = true;
            while (index < data.length()) {
                if (encrypt)
                    result[n++] = data.charAt(index);
                else
                    result[index] = data.charAt(n++);
                if (k == 0 || k == numRails - 1) {
                    index = index + 2 * (numRails - 1);
                } else if (down) {
                    index = index + 2 * (numRails - k - 1);
                    down = false;
                } else {
                    index = index + 2 * k;
                    down = true;
                }
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        RailFenceCipher railFenceCipher = new RailFenceCipher(5);
        String phrase = "ТКАЧУК АЛЕКСКЙ ОСТАЛСЯ ДОМА"; // 25/22

        String encryptedPhrase = railFenceCipher.getEncryptedData(phrase);
        System.out.println("Зашифрованная фраза: " + encryptedPhrase);

        String decryptedPhrase = railFenceCipher.getDecryptedData(encryptedPhrase);
        System.out.println("Расшифрованная фраза: " + decryptedPhrase);

    }
}