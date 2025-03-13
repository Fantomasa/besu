package org.hyperledger.besu.evm.precompile;

import org.apache.tuweni.bytes.Bytes;
import org.hyperledger.besu.evm.frame.MessageFrame;
import org.hyperledger.besu.evm.gascalculator.GasCalculator;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PermissionsConfigPrecompiledContract extends AbstractPrecompiledContract {
    /**
     * Instantiates a new Abstract precompiled contract.
     *
     * @param gasCalculator the gas calculator
     */
    public PermissionsConfigPrecompiledContract(final GasCalculator gasCalculator) {
        super("PermissionsConfigPrecompiledContract", gasCalculator);
    }

    @Override
    public long gasRequirement(final Bytes input) {
        return 0; // Adjust gas cost as needed
    }

    @Nonnull
    @Override
    public PrecompileContractResult computePrecompile(
            final Bytes input, @Nonnull final MessageFrame messageFrame) {
        try {
            String[] enodeUrls = readFileFromProject();

            Bytes bytes = encodeStringArray(enodeUrls);

            return PrecompileContractResult.success(bytes);
        } catch (Exception e) {
            // Handle errors (e.g., file not found)
            Bytes errorMessage = Bytes.wrap(("Failed to read file: " + e.getMessage()).getBytes(StandardCharsets.UTF_8));
            return PrecompileContractResult.success(errorMessage);
        }
    }

    /**
     * Reads a file from the project directory and extracts the enode URLs from the nodes-allowlist.
     *
     * @return The enode URLs as an array of strings.
     * @throws Exception If the file cannot be read or the nodes-allowlist is not found.
     */
    public String[] readFileFromProject() throws Exception {
        // String projectDir = System.getProperty("user.dir");
        String c_NODE_ROOT = "NODE_ROOT";
        String projectDir = System.getenv(c_NODE_ROOT);
        if (projectDir == null) throw new Exception("Project NODE_ROOT env is not set..");

        Path filePath = Paths.get(projectDir + "/data", "permissions_config.toml");

        java.util.List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        for (String line : lines) {
            if (line.trim().startsWith("nodes-allowlist")) {
                // Extract the array of enode URLs
                return extractEnodeUrls(line);
            }
        }

        throw new IllegalStateException("nodes-allowlist not found in the file");
    }

    /**
     * Extracts enode URLs from a line containing "nodes-allowlist".
     *
     * @param line The line containing the nodes-allowlist configuration.
     * @return An array of enode URLs.
     */
    private String[] extractEnodeUrls(final String line) {
        String enodesString = line.trim()
                .replace("nodes-allowlist=[", "")
                .replace("]", "");
            

        String[] enodes = enodesString.split(", ");

        for (int idx = 0; idx < enodes.length; idx++) {
            String enode = enodes[idx];
            String editedEnode = enode.substring(enode.indexOf("//") + "//".length(), enode.indexOf("@"));
            enodes[idx] = "0x" + editedEnode;
        }

        return enodes;
    }

    /**
     * Encodes an array of strings into Bytes.
     *
     * @param stringArray The array of strings to encode.
     * @return The Bytes representation of the array.
     */
    private Bytes encodeStringArray(final String[] stringArray) {
        String joinedString = "[" + String.join(",", stringArray) + "]";
        byte[] byteArray = joinedString.getBytes(StandardCharsets.UTF_8);
        return Bytes.wrap(byteArray);
    }
}