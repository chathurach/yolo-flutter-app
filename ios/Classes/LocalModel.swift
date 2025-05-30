// Ultralytics 🚀 AGPL-3.0 License - https://ultralytics.com/license

import CoreML

public class LocalModel: YoloModel {
  public var task: String
  var modelPath: String

  public init(modelPath: String, task: String) {
    self.modelPath = modelPath
    self.task = task
  }

  public func loadModel() async throws -> MLModel? {
    let url = try! await MLModel.compileModel(at: URL(fileURLWithPath: modelPath))
    let mlModel = try! MLModel(contentsOf: url)

    // TODO Verify task
    return mlModel
  }
}
