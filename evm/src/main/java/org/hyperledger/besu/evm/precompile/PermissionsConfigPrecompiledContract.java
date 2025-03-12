package org.hyperledger.besu.evm.precompile;

import org.apache.tuweni.bytes.Bytes;
import org.hyperledger.besu.crypto.Hash;
import org.hyperledger.besu.evm.frame.MessageFrame;
import org.hyperledger.besu.evm.gascalculator.GasCalculator;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

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
        return 0;
    }

    @Nonnull
    @Override
    public PrecompileContractResult computePrecompile(
            final Bytes input, @Nonnull final MessageFrame messageFrame) {
        return PrecompileContractResult.success(Bytes.wrap("Hello, world!".getBytes(StandardCharsets.UTF_8)));
    }
}
