Feature: Test rawcode generator service

  Scenario: Generate rawcodes from real file
    Given Rawcode file: "src/test/resources/war3map.w3t"
    When Rawcodes are parsed and read
    Then Rawcodes should be:
    """
    [rlif] Hello WOrld
    [rat9] Xyz
    [rin1] (no name provided)
    [lgdh] (no name provided)
    [I000] My Item 1
    [I001] My Item 2
    [I002] a
    """