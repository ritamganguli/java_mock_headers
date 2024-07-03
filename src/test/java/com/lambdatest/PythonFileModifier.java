package com.lambdatest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class PythonFileModifier {

    private static final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public static void modifyLineInFile(String api_url, String request_to_mock, String mock_value) {
        String filePath = "ritam.py"; // Define your file path here
        int lineNumberMockData = 7; // Line number for mock data
        String newContentMockData = "data_to_mock = '" + mock_value + "'";
        int lineNumberApiUrl = 5; // Line number for API URL
        String newContentApiUrl = "api_url = '" + api_url + "'";
        int lineNumberRequestToMock = 6; // Line number for request to mock
        String newContentRequestToMock = "request_to_mock = '" + request_to_mock + "'";

        // Get or create a lock for the specific API URL
        ReentrantLock lock = lockMap.computeIfAbsent(api_url, k -> new ReentrantLock());

        lock.lock();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            // Replace the specific lines
            lines.set(lineNumberMockData - 1, newContentMockData);
            lines.set(lineNumberApiUrl - 1, newContentApiUrl);
            lines.set(lineNumberRequestToMock - 1, newContentRequestToMock);
            // Write back to the file
            Files.write(Paths.get(filePath), lines);
            System.out.println("Lines set successfully for API URL: " + api_url);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            lock.unlock();
            // Optional: Clean up the lock map if needed
            lockMap.remove(api_url);
        }
    }
}
