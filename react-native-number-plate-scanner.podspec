require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "@logisticinfotech/react-native-number-plate-scanner"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
  @logisticinfotech/react-native-number-plate-scanner
                   DESC
  s.homepage     = "https://github.com/logisticinfotech/react-native-number-plate-scanner"
  s.license      = "MIT"
  # s.license    = { :type => "MIT", :file => "FILE_LICENSE" }
  s.authors      = { "Your Name" => "yourname@email.com" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/logisticinfotech/react-native-number-plate-scanner.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,swift}"
  s.requires_arc = true

  s.dependency "React"
  # ...
  # s.dependency "..."
end

