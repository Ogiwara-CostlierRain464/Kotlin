if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Tetris_main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Tetris_main'.");
}
var Tetris_main = function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  var Enum = Kotlin.kotlin.Enum;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var throwISE = Kotlin.throwISE;
  var MeshBasicMaterialProxy = THREE.MeshBasicMaterial;
  var LineBasicMaterialProxy = THREE.LineBasicMaterial;
  var Texture = THREE.Texture;
  var MeshLambertMaterialProxy = THREE.MeshLambertMaterial;
  var MeshPhongMaterialProxy = THREE.MeshPhongMaterial;
  var PointsMaterialProxy = THREE.PointsMaterial;
  var SpriteMaterialProxy = THREE.SpriteMaterial;
  var WebGLRendererProxy = THREE.WebGLRenderer;
  ShadowMap.prototype = Object.create(Enum.prototype);
  ShadowMap.prototype.constructor = ShadowMap;
  Shading.prototype = Object.create(Enum.prototype);
  Shading.prototype.constructor = Shading;
  MeshBasicMaterial.prototype = Object.create(MeshBasicMaterialProxy.prototype);
  MeshBasicMaterial.prototype.constructor = MeshBasicMaterial;
  LineBasicMaterial.prototype = Object.create(LineBasicMaterialProxy.prototype);
  LineBasicMaterial.prototype.constructor = LineBasicMaterial;
  MeshLambertMaterial.prototype = Object.create(MeshLambertMaterialProxy.prototype);
  MeshLambertMaterial.prototype.constructor = MeshLambertMaterial;
  MeshPhongMaterial.prototype = Object.create(MeshPhongMaterialProxy.prototype);
  MeshPhongMaterial.prototype.constructor = MeshPhongMaterial;
  PointsMaterial.prototype = Object.create(PointsMaterialProxy.prototype);
  PointsMaterial.prototype.constructor = PointsMaterial;
  SpriteMaterial.prototype = Object.create(SpriteMaterialProxy.prototype);
  SpriteMaterial.prototype.constructor = SpriteMaterial;
  WebGLRenderer.prototype = Object.create(WebGLRendererProxy.prototype);
  WebGLRenderer.prototype.constructor = WebGLRenderer;
  function main(args) {
  }
  function ShadowMap(name, ordinal, value) {
    Enum.call(this);
    this.value = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function ShadowMap_initFields() {
    ShadowMap_initFields = function () {
    };
    ShadowMap$BasicShadowMap_instance = new ShadowMap('BasicShadowMap', 0, 0);
    ShadowMap$PCFShadowMap_instance = new ShadowMap('PCFShadowMap', 1, 1);
    ShadowMap$PCFSoftShadowMap_instance = new ShadowMap('PCFSoftShadowMap', 2, 2);
  }
  var ShadowMap$BasicShadowMap_instance;
  function ShadowMap$BasicShadowMap_getInstance() {
    ShadowMap_initFields();
    return ShadowMap$BasicShadowMap_instance;
  }
  var ShadowMap$PCFShadowMap_instance;
  function ShadowMap$PCFShadowMap_getInstance() {
    ShadowMap_initFields();
    return ShadowMap$PCFShadowMap_instance;
  }
  var ShadowMap$PCFSoftShadowMap_instance;
  function ShadowMap$PCFSoftShadowMap_getInstance() {
    ShadowMap_initFields();
    return ShadowMap$PCFSoftShadowMap_instance;
  }
  ShadowMap.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ShadowMap',
    interfaces: [Enum]
  };
  function ShadowMap$values() {
    return [ShadowMap$BasicShadowMap_getInstance(), ShadowMap$PCFShadowMap_getInstance(), ShadowMap$PCFSoftShadowMap_getInstance()];
  }
  ShadowMap.values = ShadowMap$values;
  function ShadowMap$valueOf(name) {
    switch (name) {
      case 'BasicShadowMap':
        return ShadowMap$BasicShadowMap_getInstance();
      case 'PCFShadowMap':
        return ShadowMap$PCFShadowMap_getInstance();
      case 'PCFSoftShadowMap':
        return ShadowMap$PCFSoftShadowMap_getInstance();
      default:throwISE('No enum constant three.ShadowMap.' + name);
    }
  }
  ShadowMap.valueOf_61zpoe$ = ShadowMap$valueOf;
  function Shading(name, ordinal, value) {
    Enum.call(this);
    this.value = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Shading_initFields() {
    Shading_initFields = function () {
    };
    Shading$FlatShading_instance = new Shading('FlatShading', 0, 1);
    Shading$SmoothShading_instance = new Shading('SmoothShading', 1, 2);
  }
  var Shading$FlatShading_instance;
  function Shading$FlatShading_getInstance() {
    Shading_initFields();
    return Shading$FlatShading_instance;
  }
  var Shading$SmoothShading_instance;
  function Shading$SmoothShading_getInstance() {
    Shading_initFields();
    return Shading$SmoothShading_instance;
  }
  Shading.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Shading',
    interfaces: [Enum]
  };
  function Shading$values() {
    return [Shading$FlatShading_getInstance(), Shading$SmoothShading_getInstance()];
  }
  Shading.values = Shading$values;
  function Shading$valueOf(name) {
    switch (name) {
      case 'FlatShading':
        return Shading$FlatShading_getInstance();
      case 'SmoothShading':
        return Shading$SmoothShading_getInstance();
      default:throwISE('No enum constant three.Shading.' + name);
    }
  }
  Shading.valueOf_61zpoe$ = Shading$valueOf;
  function intersection() {
  }
  intersection.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'intersection',
    interfaces: []
  };
  function MeshBasicMaterial(params) {
    MeshBasicMaterialProxy.call(this, params.asDynamic);
  }
  MeshBasicMaterial.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MeshBasicMaterial',
    interfaces: []
  };
  function MeshBasicMaterialParam() {
    this.asDynamic = null;
    this.color_3e7ey6$_0 = 0;
    this.wireframe_waurrr$_0 = false;
    this.opacity_574m4a$_0 = 0.0;
    this.transparent_1f6i1t$_0 = false;
    this.visible_vebz3z$_0 = true;
    this.map_bgqfdh$_0 = null;
  }
  Object.defineProperty(MeshBasicMaterialParam.prototype, 'color', {
    get: function () {
      return this.color_3e7ey6$_0;
    },
    set: function (value) {
      this.asDynamic.color = value;
    }
  });
  Object.defineProperty(MeshBasicMaterialParam.prototype, 'wireframe', {
    get: function () {
      return this.wireframe_waurrr$_0;
    },
    set: function (value) {
      this.asDynamic.wireframe = value;
    }
  });
  Object.defineProperty(MeshBasicMaterialParam.prototype, 'opacity', {
    get: function () {
      return this.opacity_574m4a$_0;
    },
    set: function (value) {
      this.asDynamic.opacity = value;
    }
  });
  Object.defineProperty(MeshBasicMaterialParam.prototype, 'transparent', {
    get: function () {
      return this.transparent_1f6i1t$_0;
    },
    set: function (value) {
      this.asDynamic.transparent = value;
    }
  });
  Object.defineProperty(MeshBasicMaterialParam.prototype, 'visible', {
    get: function () {
      return this.visible_vebz3z$_0;
    },
    set: function (value) {
      this.asDynamic.visible = value;
    }
  });
  Object.defineProperty(MeshBasicMaterialParam.prototype, 'map', {
    get: function () {
      return this.map_bgqfdh$_0;
    },
    set: function (value) {
      this.asDynamic.map = value;
    }
  });
  MeshBasicMaterialParam.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MeshBasicMaterialParam',
    interfaces: []
  };
  function MeshBasicMaterialParam_init($this) {
    $this = $this || Object.create(MeshBasicMaterialParam.prototype);
    MeshBasicMaterialParam_init_0(new Object(), $this);
    return $this;
  }
  function MeshBasicMaterialParam_init_0(asDynamic, $this) {
    $this = $this || Object.create(MeshBasicMaterialParam.prototype);
    MeshBasicMaterialParam.call($this);
    $this.asDynamic = asDynamic;
    return $this;
  }
  function LineBasicMaterial(params) {
    LineBasicMaterialProxy.call(this, params.asDynamic);
  }
  LineBasicMaterial.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LineBasicMaterial',
    interfaces: []
  };
  function LineBasicMaterialParam() {
    this.asDynamic = LineBasicMaterialParam$asDynamic$lambda;
    this.color_dvni25$_0 = 0;
    this.wireframe_ukfr9q$_0 = false;
    this.opacity_fux2y3$_0 = 0.0;
    this.transparent_vdywqk$_0 = false;
  }
  Object.defineProperty(LineBasicMaterialParam.prototype, 'color', {
    get: function () {
      return this.color_dvni25$_0;
    },
    set: function (value) {
      this.asDynamic.color = value;
    }
  });
  Object.defineProperty(LineBasicMaterialParam.prototype, 'wireframe', {
    get: function () {
      return this.wireframe_ukfr9q$_0;
    },
    set: function (value) {
      this.asDynamic.wireframe = value;
    }
  });
  Object.defineProperty(LineBasicMaterialParam.prototype, 'opacity', {
    get: function () {
      return this.opacity_fux2y3$_0;
    },
    set: function (value) {
      this.asDynamic.opacity = value;
    }
  });
  Object.defineProperty(LineBasicMaterialParam.prototype, 'transparent', {
    get: function () {
      return this.transparent_vdywqk$_0;
    },
    set: function (value) {
      this.asDynamic.transparent = value;
    }
  });
  function LineBasicMaterialParam$asDynamic$lambda() {
    return Unit;
  }
  LineBasicMaterialParam.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LineBasicMaterialParam',
    interfaces: []
  };
  function MeshLambertMaterialParam() {
    this.asDynamic = null;
    this.color_o1tnup$_0 = 0;
    this.wireframe_7lhb0m$_0 = false;
    this.opacity_rn41pl$_0 = 0.0;
    this.map_gbbokm$_0 = new Texture();
    this.lightMap_q17vhw$_0 = new Texture();
    this.lightMapIntensity_drbm0l$_0 = 0.0;
    this.reflectivity_2vz57y$_0 = 0.0;
    this.refractionRatio_fl8jro$_0 = 0.0;
  }
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'color', {
    get: function () {
      return this.color_o1tnup$_0;
    },
    set: function (value) {
      this.asDynamic.color = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'wireframe', {
    get: function () {
      return this.wireframe_7lhb0m$_0;
    },
    set: function (value) {
      this.asDynamic.wireframe = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'opacity', {
    get: function () {
      return this.opacity_rn41pl$_0;
    },
    set: function (value) {
      this.asDynamic.opacity = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'map', {
    get: function () {
      return this.map_gbbokm$_0;
    },
    set: function (value) {
      this.asDynamic.map = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'lightMap', {
    get: function () {
      return this.lightMap_q17vhw$_0;
    },
    set: function (value) {
      this.asDynamic.lightMap = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'lightMapIntensity', {
    get: function () {
      return this.lightMapIntensity_drbm0l$_0;
    },
    set: function (value) {
      this.asDynamic.lightMapIntensity = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'reflectivity', {
    get: function () {
      return this.reflectivity_2vz57y$_0;
    },
    set: function (value) {
      this.asDynamic.reflectivity = value;
    }
  });
  Object.defineProperty(MeshLambertMaterialParam.prototype, 'refractionRatio', {
    get: function () {
      return this.refractionRatio_fl8jro$_0;
    },
    set: function (value) {
      this.asDynamic.refractionRatio = value;
    }
  });
  MeshLambertMaterialParam.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MeshLambertMaterialParam',
    interfaces: []
  };
  function MeshLambertMaterialParam_init($this) {
    $this = $this || Object.create(MeshLambertMaterialParam.prototype);
    MeshLambertMaterialParam_init_0(new Object(), $this);
    return $this;
  }
  function MeshLambertMaterialParam_init_0(asDynamic, $this) {
    $this = $this || Object.create(MeshLambertMaterialParam.prototype);
    MeshLambertMaterialParam.call($this);
    $this.asDynamic = asDynamic;
    return $this;
  }
  function MeshLambertMaterial(params) {
    MeshLambertMaterialProxy.call(this, params.asDynamic);
  }
  MeshLambertMaterial.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MeshLambertMaterial',
    interfaces: []
  };
  function MeshPhongMaterial(params) {
    MeshPhongMaterialProxy.call(this, params.asDynamic);
  }
  MeshPhongMaterial.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MeshPhongMaterial',
    interfaces: []
  };
  function MeshPhongMaterialParam() {
    this.asDynamic = null;
    this.color_akbyia$_0 = 0;
    this.specular_7xygjg$_0 = 0;
    this.shininess_ov6ff5$_0 = 0;
    this.shading_6rwjp1$_0 = 0;
    this.map_qp58ih$_0 = new Texture();
  }
  Object.defineProperty(MeshPhongMaterialParam.prototype, 'color', {
    get: function () {
      return this.color_akbyia$_0;
    },
    set: function (value) {
      this.asDynamic.color = value;
    }
  });
  Object.defineProperty(MeshPhongMaterialParam.prototype, 'specular', {
    get: function () {
      return this.specular_7xygjg$_0;
    },
    set: function (value) {
      this.asDynamic.specular = value;
    }
  });
  Object.defineProperty(MeshPhongMaterialParam.prototype, 'shininess', {
    get: function () {
      return this.shininess_ov6ff5$_0;
    },
    set: function (value) {
      this.asDynamic.shininess = value;
    }
  });
  Object.defineProperty(MeshPhongMaterialParam.prototype, 'shading', {
    get: function () {
      return this.shading_6rwjp1$_0;
    },
    set: function (value) {
      this.asDynamic.shading = value;
    }
  });
  Object.defineProperty(MeshPhongMaterialParam.prototype, 'map', {
    get: function () {
      return this.map_qp58ih$_0;
    },
    set: function (value) {
      this.asDynamic.map = value;
    }
  });
  MeshPhongMaterialParam.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MeshPhongMaterialParam',
    interfaces: []
  };
  function MeshPhongMaterialParam_init($this) {
    $this = $this || Object.create(MeshPhongMaterialParam.prototype);
    MeshPhongMaterialParam_init_0(new Object(), $this);
    return $this;
  }
  function MeshPhongMaterialParam_init_0(asDynamic, $this) {
    $this = $this || Object.create(MeshPhongMaterialParam.prototype);
    MeshPhongMaterialParam.call($this);
    $this.asDynamic = asDynamic;
    return $this;
  }
  function PointsMaterial(params) {
    PointsMaterialProxy.call(this, params.asDynamic);
  }
  PointsMaterial.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PointsMaterial',
    interfaces: []
  };
  function PointsMaterialParam() {
    this.asDynamic = null;
    this.color_y40i2t$_0 = 0;
  }
  Object.defineProperty(PointsMaterialParam.prototype, 'color', {
    get: function () {
      return this.color_y40i2t$_0;
    },
    set: function (value) {
      this.asDynamic.color = value;
    }
  });
  PointsMaterialParam.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PointsMaterialParam',
    interfaces: []
  };
  function PointsMaterialParam_init($this) {
    $this = $this || Object.create(PointsMaterialParam.prototype);
    PointsMaterialParam_init_0(new Object(), $this);
    return $this;
  }
  function PointsMaterialParam_init_0(asDynamic, $this) {
    $this = $this || Object.create(PointsMaterialParam.prototype);
    PointsMaterialParam.call($this);
    $this.asDynamic = asDynamic;
    return $this;
  }
  function SpriteMaterial(params) {
    SpriteMaterialProxy.call(this, params.asDynamic);
  }
  SpriteMaterial.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SpriteMaterial',
    interfaces: []
  };
  function SpriteMaterialParam() {
    this.asDynamic = null;
    this.color_o15k57$_0 = 0;
    this.opacity_9rwrdf$_0 = 0.0;
    this.map_ptpw82$_0 = new Texture();
  }
  Object.defineProperty(SpriteMaterialParam.prototype, 'color', {
    get: function () {
      return this.color_o15k57$_0;
    },
    set: function (value) {
      this.asDynamic.color = value;
    }
  });
  Object.defineProperty(SpriteMaterialParam.prototype, 'opacity', {
    get: function () {
      return this.opacity_9rwrdf$_0;
    },
    set: function (value) {
      this.asDynamic.opacity = value;
    }
  });
  Object.defineProperty(SpriteMaterialParam.prototype, 'map', {
    get: function () {
      return this.map_ptpw82$_0;
    },
    set: function (value) {
      this.asDynamic.map = value;
    }
  });
  SpriteMaterialParam.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SpriteMaterialParam',
    interfaces: []
  };
  function SpriteMaterialParam_init($this) {
    $this = $this || Object.create(SpriteMaterialParam.prototype);
    SpriteMaterialParam.call($this);
    $this.asDynamic = SpriteMaterialParam_init$lambda;
    return $this;
  }
  function SpriteMaterialParam_init$lambda() {
    return Unit;
  }
  function WebGLRenderer() {
  }
  WebGLRenderer.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'WebGLRenderer',
    interfaces: []
  };
  function WebGLRenderer_init($this) {
    $this = $this || Object.create(WebGLRenderer.prototype);
    WebGLRendererProxy.call($this);
    WebGLRenderer.call($this);
    return $this;
  }
  function WebGLRenderer_init_0(params, $this) {
    $this = $this || Object.create(WebGLRenderer.prototype);
    WebGLRendererProxy.call($this, params.asDynamic);
    WebGLRenderer.call($this);
    return $this;
  }
  function WebGLRendererParams() {
    this.asDynamic = WebGLRendererParams$asDynamic$lambda;
    this.antialias_yx6094$_0 = false;
  }
  Object.defineProperty(WebGLRendererParams.prototype, 'antialias', {
    get: function () {
      return this.antialias_yx6094$_0;
    },
    set: function (value) {
      this.asDynamic.antialias = value;
    }
  });
  function WebGLRendererParams$asDynamic$lambda() {
    return Unit;
  }
  WebGLRendererParams.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'WebGLRendererParams',
    interfaces: []
  };
  _.main_kand9s$ = main;
  Object.defineProperty(ShadowMap, 'BasicShadowMap', {
    get: ShadowMap$BasicShadowMap_getInstance
  });
  Object.defineProperty(ShadowMap, 'PCFShadowMap', {
    get: ShadowMap$PCFShadowMap_getInstance
  });
  Object.defineProperty(ShadowMap, 'PCFSoftShadowMap', {
    get: ShadowMap$PCFSoftShadowMap_getInstance
  });
  var package$three = _.three || (_.three = {});
  package$three.ShadowMap = ShadowMap;
  Object.defineProperty(Shading, 'FlatShading', {
    get: Shading$FlatShading_getInstance
  });
  Object.defineProperty(Shading, 'SmoothShading', {
    get: Shading$SmoothShading_getInstance
  });
  package$three.Shading = Shading;
  var package$core = package$three.core || (package$three.core = {});
  package$core.intersection = intersection;
  var package$materials = package$three.materials || (package$three.materials = {});
  var package$basic = package$materials.basic || (package$materials.basic = {});
  package$basic.MeshBasicMaterial = MeshBasicMaterial;
  package$basic.MeshBasicMaterialParam_init = MeshBasicMaterialParam_init;
  package$basic.MeshBasicMaterialParam = MeshBasicMaterialParam;
  var package$linebasic = package$materials.linebasic || (package$materials.linebasic = {});
  package$linebasic.LineBasicMaterial = LineBasicMaterial;
  package$linebasic.LineBasicMaterialParam = LineBasicMaterialParam;
  var package$meshlambert = package$materials.meshlambert || (package$materials.meshlambert = {});
  package$meshlambert.MeshLambertMaterialParam_init = MeshLambertMaterialParam_init;
  package$meshlambert.MeshLambertMaterialParam = MeshLambertMaterialParam;
  package$meshlambert.MeshLambertMaterial = MeshLambertMaterial;
  var package$phong = package$materials.phong || (package$materials.phong = {});
  package$phong.MeshPhongMaterial = MeshPhongMaterial;
  package$phong.MeshPhongMaterialParam_init = MeshPhongMaterialParam_init;
  package$phong.MeshPhongMaterialParam = MeshPhongMaterialParam;
  var package$points = package$materials.points || (package$materials.points = {});
  package$points.PointsMaterial = PointsMaterial;
  package$points.PointsMaterialParam_init = PointsMaterialParam_init;
  package$points.PointsMaterialParam = PointsMaterialParam;
  var package$sprite = package$materials.sprite || (package$materials.sprite = {});
  package$sprite.SpriteMaterial = SpriteMaterial;
  package$sprite.SpriteMaterialParam_init = SpriteMaterialParam_init;
  package$sprite.SpriteMaterialParam = SpriteMaterialParam;
  var package$renderers = package$three.renderers || (package$three.renderers = {});
  var package$webglrenderer = package$renderers.webglrenderer || (package$renderers.webglrenderer = {});
  package$webglrenderer.WebGLRenderer_init = WebGLRenderer_init;
  package$webglrenderer.WebGLRenderer_init_vk0fzy$ = WebGLRenderer_init_0;
  package$webglrenderer.WebGLRenderer = WebGLRenderer;
  package$webglrenderer.WebGLRendererParams = WebGLRendererParams;
  main([]);
  Kotlin.defineModule('Tetris_main', _);
  return _;
}(typeof Tetris_main === 'undefined' ? {} : Tetris_main, kotlin);
