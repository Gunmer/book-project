package com.gunmer.book

import io.vertx.core.DeploymentOptions
import io.vertx.core.json.Json
import io.vertx.kotlin.coroutines.CoroutineVerticle
import java.util.logging.Logger

class MainVerticle : CoroutineVerticle() {

  private val log = Logger.getLogger(this.javaClass.simpleName)

  override suspend fun start() {
    super.start()
    Json.mapper.findAndRegisterModules()

    val workerOption = DeploymentOptions()
      .setInstances(4)
      .setWorker(true)

    val deploymentOptions = DeploymentOptions()
      .setInstances(4)
      .setWorker(false)

    vertx.deployVerticle("com.gunmer.book.api.ServerVerticle", deploymentOptions)
    vertx.deployVerticle("com.gunmer.book.domain.interactors.FindBookByIsbnInteractor", workerOption)
  }

}
