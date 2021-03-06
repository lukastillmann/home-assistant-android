package io.homeassistant.companion.android.domain.integration

import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object IntegrationUseCaseImplSpec : Spek({
    describe("integration use case") {

        val integrationRepository by memoized {
            mockk<IntegrationRepository>(
                relaxed = true,
                relaxUnitFun = true
            )
        }
        val useCase by memoized { IntegrationUseCaseImpl(integrationRepository) }

        describe("registerDevice") {
            val deviceRegistration = mockk<DeviceRegistration>()
            beforeEachTest {
                coEvery {
                    integrationRepository.registerDevice(any())
                } just Runs

                runBlocking {
                    useCase.registerDevice(deviceRegistration)
                }
            }

            it("should call repository") {
                coVerify {
                    integrationRepository.registerDevice(deviceRegistration)
                }
            }
        }

        describe("updateRegistration") {
            beforeEachTest {
                coEvery {
                    integrationRepository.updateRegistration(any())
                } just Runs

                runBlocking {
                    useCase.updateRegistration("1", "2", "3", "4", "5", "6", "7")
                }
            }

            it("should call repository") {
                coVerify {
                    integrationRepository.updateRegistration(DeviceRegistration(
                        "1",
                        "2",
                        "7"
                        ))
                }
            }
        }

        describe("isRegistered") {
            beforeEachTest {
                runBlocking { useCase.isRegistered() }
            }

            it("should call the repository") {
                coVerify { integrationRepository.isRegistered() }
            }
        }

        describe("getUiUrl") {
            beforeEachTest {
                runBlocking { useCase.getUiUrl(true) }
            }

            it("should call the repository") {
                coVerify { integrationRepository.getUiUrl(true) }
            }
        }

        describe("updateLocation") {
            val location = mockk<UpdateLocation>()
            beforeEachTest {
                runBlocking { useCase.updateLocation(location) }
            }

            it("should call the repository") {
                coVerify { integrationRepository.updateLocation(location) }
            }
        }

        describe("getZones") {
            beforeEachTest {
                runBlocking { useCase.getZones() }
            }

            it("should call the repository") {
                coVerify { integrationRepository.getZones() }
            }
        }

        describe("setZoneTrackingEnabled") {
            beforeEachTest {
                runBlocking { useCase.setZoneTrackingEnabled(true) }
            }

            it("should call the repository") {
                coVerify { integrationRepository.setZoneTrackingEnabled(true) }
            }
        }

        describe("isZoneTrackingEnabled") {
            beforeEachTest {
                runBlocking { useCase.isZoneTrackingEnabled() }
            }

            it("should call the repository") {
                coVerify { integrationRepository.isZoneTrackingEnabled() }
            }
        }

        describe("setBackgroundTrackingEnabled") {
            beforeEachTest {
                runBlocking { useCase.setBackgroundTrackingEnabled(true) }
            }

            it("should call the repository") {
                coVerify { integrationRepository.setBackgroundTrackingEnabled(true) }
            }
        }

        describe("isBackgroundTrackingEnabled") {
            beforeEachTest {
                runBlocking { useCase.isBackgroundTrackingEnabled() }
            }

            it("should call the repository") {
                coVerify { integrationRepository.isBackgroundTrackingEnabled() }
            }
        }
    }
})
