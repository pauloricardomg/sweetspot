require "httpclient"
require "json"

class Api
    DOMAIN = "localhost:8080"

    def initialize()
        @global_params = {"Content-Type" => "application/json"}
        @url = "http://#{DOMAIN}"
        @http = HTTPClient.new
    end

    # Simple Get
    def get(path, params={})
        return @http.get_content("#{@url}#{path}", params.merge(@global_params))
    end

    # Simple Post
    def post(path, body, params={})
        return @http.post("#{@url}#{path}", body, params.merge(@global_params))
    end
end
