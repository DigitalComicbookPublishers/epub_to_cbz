module EpubToCbz
  module ComicbookBuilder
    class << self

      attr_reader :epub_path
      attr_reader :opf

      def call(epub_path, options: {})
        if !File.exists?(epub_path)
          raise ArgumentError, "#{epub_path} does not exist"
        end

        @epub_path = epub_path
        @images_path = options[:images_path]

        build
      end

      private

      def build
        opf = find_opf
      end

      def find_opf
        Zip::File.open(epub_path) do |zip_file|

          entry = zip_file.glob('**/*.opf').first
          entry.get_input_stream.read
        end
      end
    end
  end
end
