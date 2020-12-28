package com.siso.web.region;

import com.siso.Result.Result;
import com.siso.request.web.region.AddRegionRequest;
import com.siso.response.web.region.RegionResponse;

import java.util.List;

public interface RegionService {

    //获取省
    Result<List<RegionResponse>> region();

    Result<String>addRegion(AddRegionRequest request);


}
