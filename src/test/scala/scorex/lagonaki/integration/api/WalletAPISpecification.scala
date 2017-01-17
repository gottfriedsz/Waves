package scorex.lagonaki.integration.api

import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}
import scorex.crypto.encode.Base58

class WalletAPISpecification extends FunSuite with Matchers with BeforeAndAfterAll   with scorex.waves.TestingCommons {


  override def beforeAll(): Unit = {
    stopGeneration(applications)
  }

  override def afterAll(): Unit = {
    stop()
  }

  test("/wallet/ API route") {
    (GET.request("/wallet") \ "exists").as[Boolean] shouldBe true
  }

  test("/wallet/seed API route") {
    GET.incorrectApiKeyTest("/wallet/seed")

    val response = GET.request("/wallet/seed", headers = Map("api_key" -> "test"))
    (response \ "seed").as[String] shouldBe Base58.encode(application.settings.walletSeed.get)
  }
}
