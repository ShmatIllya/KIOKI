package LSB;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.IOUtils;

public class LSB {
    public static void encode(String audioFilePath, String message, String outputFilePath) throws Exception {
        // Чтение аудио файла
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(audioFilePath));

        byte[] headerData = new byte[44];
        System.arraycopy(bytes, 0, headerData, 0, headerData.length);

        // Конвертация сообщения в байты
        byte[] messageBytes = message.getBytes();

        // Проверка аудио файла на вместительность сообщения
        int audioCapacity = (bytes.length - 44) / 8;
        if (audioCapacity < messageBytes.length) {
            throw new Exception("Audio file is too small to hold the message");
        }

        // Зашифровка сообщения в аудио файле
        int[] bits = new int[8]; // Массив для хранения битов
        int lastIndex = 44;
        for (byte messageByte : messageBytes) {
            for (int j = 7; j >= 0; j--) {
                bits[j] = (messageByte >> j) & 0b00000001;
            }

            // Шифрование битов в аудио байты
            for (int j = 0; j < 8; j++) {
                // Извлечение аудио байта
                byte audioByte = bytes[lastIndex];
                audioByte = (byte) ((bits[j] == 1) ? (audioByte | 0b00000001) : (audioByte & 0b11111110));
                bytes[lastIndex] = audioByte; // Обновление аудио байтов в массиве
                lastIndex++;
            }
        }

        int newDataLength = bytes.length - headerData.length;
        headerData[40] = (byte) (newDataLength & 0xff);
        headerData[41] = (byte) ((newDataLength >> 8) & 0xff);
        headerData[42] = (byte) ((newDataLength >> 16) & 0xff);
        headerData[43] = (byte) ((newDataLength >> 24) & 0xff);

        // Запись обновленного аудио в файл
        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath, true)) {
            outputStream.write(headerData);
            outputStream.write(bytes, headerData.length, newDataLength);
        }
    }

    public static void decode(String audioFilePath) throws Exception {
        // Чтение аудио файла
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(audioFilePath));
        System.arraycopy(bytes, 44, bytes, 0, bytes.length - 44);

        // Декодирование сообщение из файла
        byte[] messageBytes = new byte[bytes.length / 8];
        int[] bits = new int[8]; // Массив для хранения битов
        int i = 0;
        int messageIndex = 0;
        for (byte audioByte : bytes) {
            bits[i] = audioByte & 0b00000001;

            if (i == 7) {
                messageBytes[messageIndex] = 0b00000000;
                for (int k = 7; k >= 0; k--) {
                    messageBytes[messageIndex] = (byte) ((messageBytes[messageIndex] << 1) | bits[k]);
                }
                messageIndex++;
                i = 0;
                continue;
            }
            i++;
        }
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("decoded.txt"))) {
            stream.write(messageBytes);
        }
    }
}
